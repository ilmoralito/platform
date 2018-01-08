//= require jquery/jquery
//= require bootstrap/js/bootstrap
//= require bootstrap-datepicker/js/bootstrap-datepicker
//= require axios/axios
//= require select2/js/select2
//= require_self

$(document).ready(() => {
    // $('#employeeList').select2({theme: 'bootstrap'});

    $('#datepicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd',
        startDate: new Date(),
    });

    // Toggle fixed voucher table row
    {
        const employees = document.querySelectorAll('.employees');

        employees.forEach(employee => {
            employee.addEventListener('change', toggleEmployee);
        });

        function getEmployeesToToggle(id) {
            return document.querySelectorAll(`tr.${id}`);
        }

        function updateTotalPrice(targets, status) {
            const totalPriceTarget = document.querySelector('#totalPrice');
            let totalPrice = parseInt(totalPriceTarget.innerHTML, 10)

            targets.forEach(target => {
                const price = parseInt(target.querySelector('.price').innerHTML, 10);

                if (status) {
                    totalPrice += price;
                } else {
                    totalPrice -= price;
                }

                totalPriceTarget.innerHTML = totalPrice;
            });
        }

        function toggleEmployee() {
            const targets = getEmployeesToToggle(this.id);

            targets.forEach(target => {
                // toggle class
                target.classList.toggle('hide');

                // set fixed voucher checkbox checked to false
                target.querySelector('input[type=checkbox]').checked = false;
            });

            updateTotalPrice(targets, this.checked);
        }
    }

    // Toggle fixed voucher checkboxes to print
    {
        var checkboxTrigger = document.querySelector('#checkboxTrigger');

        checkboxTrigger.addEventListener('change', toggleCheckboxes);

        function toggleCheckboxes() {
            const checkboxTriggerStatus = this.checked;
            const fixedVouchers = getFixedVouchersToPrint();

            fixedVouchers.forEach(element => {
                element.checked = checkboxTriggerStatus;
            });
        }

        function getFixedVouchersToPrint() {
            return document.querySelectorAll('tr:not(.hide) input[type=checkbox]');
        }
    }
})

function h(e) {
    $(e).css({
        'height':'auto',
        'overflow-y':'hidden'
    }).height(e.scrollHeight);
}

$('textarea#description').each(function () {
    h(this);
}).on('input', function () {
    h(this);
});

{
    $('#startDateAndTime').datepicker({
        todayHighlight: true,
        format: 'yyyy-mm-dd',
        autoclose: true,
        startDate: '+3d'
    });
}

// VOUCHER
{
    // Adding and substracting price
    const price = document.querySelector('#price');

    document.querySelectorAll('.prices').forEach(price => {
        price.addEventListener('change', applyPrice);
    });

    function applyPrice(e) {
        const selectedPrice = parseInt(this.dataset.price, 10);

        if (this.checked) {
            addPrice(selectedPrice);

            return;
        }

        substractPrice(selectedPrice);
    }

    function getActualPrice() {
        return parseInt(price.value, 10);
    }

    function addPrice(selectedPrice) {
        const actualPrice = getActualPrice();
        const newPrice = actualPrice + selectedPrice;

        price.value = newPrice;
    }

    function substractPrice(selectedPrice) {
        const actualPrice = getActualPrice();
        const newPrice = actualPrice - selectedPrice;

        price.value = newPrice;
    }

    // Update coordination when change employee
    const employeeList = document.querySelector('#employeeList');

    employeeList.addEventListener('change', updateCoordinationList);

    function updateCoordinationList() {
        const coordinationList = JSON.parse(this.options[this.selectedIndex].dataset.coordinationList);

        renderNewCoordinationList(coordinationList);
    }

    function renderNewCoordinationList(coordinationList) {
        const target = document.querySelector('#coordination');
        const results = coordinationList.map(coordination => {
            return `
                <option value="${coordination.id}">${coordination.name}</option>
            `;
        }).join('')

        target.innerHTML = results;
    }

    // Update employee/guest list when date change
    const date = document.querySelector('#date');

    date.addEventListener('change', updateParticipantList);

    function getParticipantList(url) {
        return axios(url);
    }

    function renderParticipantList(participantList, type) {
        const target = type === 'employee' ? '#employeeList' : '#guestList';
        const result = participantList.map(participant => {
            const name = type === 'employee' ? 'employees' : 'guests';

            return `
                <div class="checkbox">
                    <label>
                        <input
                            type="checkbox"
                            name="${name}"
                            value="${participant.id}">
                        ${participant.fullName}
                    </label>
                </div>
            `;
        }).join('');

        document.querySelector(target).innerHTML = result;
    }

    async function updateParticipantList() {
        const option = this.options[this.selectedIndex];
        const activityId = this.dataset.activityId;
        const type = this.dataset.activityType;
        const token = type === 'employee' ? 'employeeList' : 'guestList';
        const url = `/${token}/${option.value}/${activityId}`;
        const participantList = await getParticipantList(url);

        renderParticipantList(participantList.data.participantList, type);
    }

    // When change coffeeshop update prices
    const coffeeShop = document.querySelector('#coffeeShop');

    coffeeShop.addEventListener('change', updatePrices);

    function updatePrices(e) {
        const breakfast = document.querySelector('#breakfast');
        const lunch = document.querySelector('#lunch');
        const dinner = document.querySelector('#dinner');
        const others = document.querySelector('#others');
        const price = document.querySelector('#price');

        const dataset = this.options[this.selectedIndex].dataset;

        price.value = 0;
        breakfast.checked = false;
        lunch.checked = false;
        dinner.checked = false;
        others.checked = false;

        breakfast.dataset.price = dataset.breakfastPrice;
        lunch.dataset.price = dataset.lunchPrice;
        dinner.dataset.price = dataset.dinnerPrice;
        others.dataset.price = dataset.othersPrice;
    }
}

// LOCATION
// Notify when the number of participants exceeds the capacity of the selected classroom
{
    const participants = document.querySelector('#participants');
    const place = document.querySelector('#place');

    function check() {
        const numberOfparticipants = parseInt(participants.value, 10);
        const helpBlock = document.querySelector('#helpBlock');
        const capacity = getPlaceCapacity();

        if (numberOfparticipants > capacity) {
            helpBlock.innerHTML = 'El numero de participantes supera la capicada del aula';

            return;
        }

        helpBlock.innerHTML = '';
    }

    function getPlaceCapacity() {
        const dataset = getDataset();

        return parseInt(dataset.classroomCapacity, 10);
    }

    function getDataset() {
        const dataset = place.options[place.selectedIndex].dataset;

        return dataset;
    }

    participants.addEventListener('blur', check);
    place.addEventListener('change', check);
}

// Show classroom information
{
    const placeDetailTrigger = document.querySelector('#place-details-trigger');
    const placeDetailContainer = document.querySelector('#place-details-container');

    function handleTrigger() {
        if (placeDetailContainer.innerHTML === '') {
            renderPlaceDetail();

            return
        }

        placeDetailContainer.innerHTML = '';
    }

    function handleChangePlace() {
        if (placeDetailContainer.innerHTML !== '') {
            renderPlaceDetail();
        }
    }

    function renderPlaceDetail() {
        const dataset = getDataset();

        placeDetailContainer.innerHTML = `
            <table class="table table-bordered table-condensed">
                <colgroup>
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 80%;">
                </colgroup>

                <tbody>
                    <tr>
                        <td>WIFI</td>
                        <td>${dataset.classroomWifi === 'true' ? 'Si' : 'No'}</td>
                    </tr>

                    <tr>
                        <td>Climatizado</td>
                        <td>${dataset.classroomAirconditioned === 'true' ? 'Si' : 'No'}</td>
                    </tr>

                    <tr>
                        <td>Capacidad</td>
                        <td>${dataset.classroomCapacity}</td>
                    </tr>

                    <tr>
                        <td>Numero de toma corrientes</td>
                        <td>${dataset.classroomPoweroutletnumber}</td>
                    </tr>
                </tbody>
            </table>
        `;
    }

    function getDataset() {
        const place = document.querySelector('#place');
        const dataset = place.options[place.selectedIndex].dataset;

        return Object.assign({}, dataset);
    }

    placeDetailTrigger.addEventListener('click', handleTrigger);
    place.addEventListener('change', handleChangePlace);
}

// Cleaner
{
    const cleaner = document.querySelector('#cleaner');

    function clean(e) {
        e.preventDefault();

        const refreshments = document.querySelector('#refreshments');
        const elements = Array.from(refreshments.querySelectorAll('input'));

        elements.forEach(element => {
            element.type === 'radio' ? element.checked = false : element.value = '';
        });
    }

    cleaner.addEventListener('click', clean);
}

// Toggle bookmarks
{
    document.querySelectorAll('a.toggleTicketBookmark').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            e.preventDefault();

            const url = '/bookmarks/toggle'
            const params = new URLSearchParams();

            params.append('ticketId', this.dataset.ticketId);
            params.append('employeeId', this.dataset.employeeId);

            axios({
                url: url,
                method: 'post',
                responseType: 'json',
                data: params
            }).then(response => {
                const target = document.querySelector(`#glyphicon-bookmark-${this.dataset.ticketId}`);

                if (this.dataset.controllerName == 'ticket') {
                    target.style.color = target.style.color ? '#337AB7' : '#DDDDDD';
                } else {
                    document.querySelector(`#ticket_${this.dataset.ticketId}`).outerHTML = '';
                }
            }).catch(error => {
                console.error(error);
            })
        });
    });
}

// Change employee fullName property from profile view
{
    const editButton = document.querySelector('#edit');
    const target = document.querySelector('#target');
    const orginalFullName = target.innerHTML;
    const actionButtons = document.querySelector('#actionButtons');

    function edit() {
        const text = editButton.innerHTML;

        if (text === 'Editar') {
            editButton.innerHTML = 'Confirmar';
            drawInput();

            drawCancelButton();
        } else {
            updateFullName(document.querySelector('#newFullName').value);
        }
    }

    function updateFullName(fullName) {
        const url = '/employees/' + editButton.dataset.id + '/updateFullName'

        axios({
            url: url,
            method: 'put',
            params: {
                employeeId: editButton.dataset.id,
                fullName: fullName
            }
        })
        .then(response => {
            done(fullName);
        })
        .catch(error => {
            displayHelpBlockMessage(error.message);
        });
    }

    function displayHelpBlockMessage(message) {
        const helpBlock = document.querySelector('#helpBlock');

        helpBlock.textContent = message;

        const timeoutID = window.setTimeout(() => {
            helpBlock.textContent = '';

            window.clearTimeout(timeoutID);
        }, 3000);
    }

    function drawInput() {
        const row = document.createElement('div');
        const col = document.createElement('div');
        const formGroup = document.createElement('div')
        const input = document.createElement('input');
        const helpBlock = document.createElement('small');

        helpBlock.setAttribute('id', 'helpBlock');
        helpBlock.setAttribute('class', 'helpBlock');

        row.setAttribute('class', 'row');
        col.setAttribute('class', 'col-md-12');
        formGroup.setAttribute('class', 'form-group');

        input.setAttribute('id', 'newFullName');
        input.setAttribute('class', 'form-control');
        input.setAttribute('value', target.innerHTML);

        input.addEventListener('keypress', keyPressed.bind(this));

        target.innerHTML = '';

        formGroup.appendChild(input);
        formGroup.appendChild(helpBlock);
        col.appendChild(formGroup);
        row.appendChild(col);

        target.appendChild(row);
    }

    function keyPressed(event) {
        if (event.keyCode === 13) {
            updateFullName(event.target.value);
        }
    }

    function drawCancelButton() {
        const cancelButton = document.createElement('button');

        cancelButton.setAttribute('id', 'cancel');
        cancelButton.setAttribute('class', 'btn btn-default btn-sm');

        cancelButton.textContent = 'Cancelar';

        cancelButton.addEventListener('click', done.bind('this', orginalFullName));

        actionButtons.appendChild(cancelButton);
    }

    function done(text) {
        editButton.textContent = 'Editar'
        document.querySelector('#cancel').outerHTML = '';
        target.innerHTML = text;
    }

    editButton.addEventListener('click', edit);
}

// TODO: find the right to display the next logic only in employee view
{
    const copyText = document.querySelector('#email');
    const copyButton = document.querySelector('#copy');
    const messageBox = document.querySelector('#message');

    function copy() {
        copyText.select();
        document.execCommand('Copy');

        displayMessage();
    }

    function displayMessage() {
        messageBox.innerHTML = 'Email copiado';

        clearMessage();
    }

    function clearMessage() {
        const timeoutID = window.setTimeout(() => {
           messageBox.innerHTML = '';
           window.clearTimeout(timeoutID);
        }, 2000);
    }

    copyButton.addEventListener('click', copy);
}
