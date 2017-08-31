//= require jquery-2.2.0.min
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

$('.addBookmarkTrigger').on('click', function() {
    let form = $(this).next();

    form.submit();
});

$('.deleteBookmarkTrigger').on('click', function() {
    let form = $(this).next();

    if (confirm('¿Seguro?')) {
        form.submit();
    }
});

(function() {
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
