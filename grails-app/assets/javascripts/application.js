//= require jquery-2.2.0.min
//= require axios.min.js
//= require bootstrap
//= require_self

function h(e) {
    $(e).css({'height':'auto','overflow-y':'hidden'}).height(e.scrollHeight);
}

$('textarea#description').each(function () {
    h(this);
}).on('input', function () {
    h(this);
});

$('.deleteBookmarkTrigger').on('click', function() {
    let form = $(this).next();

    if (confirm('¿Seguro?')) {
        form.submit();
    }
});

// Toggle bookmarks
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

// Change employee fullName property from profile view
(() => {
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
})();

// TODO: find the right to display the next logic only in employee view
(() => {
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
})();
