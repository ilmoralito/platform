//= require jquery/jquery
//= require bootstrap/js/bootstrap
//= require axios/axios
//= require_self

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
