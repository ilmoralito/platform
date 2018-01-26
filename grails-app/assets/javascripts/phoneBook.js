const triggers = document.querySelectorAll('.triggers');

triggers.forEach(trigger => trigger.addEventListener('click', toggle));

function toggle(event) {
    const extensionNumber = event.target.textContent.trim();

    document.querySelector(`#pb_${extensionNumber}`).classList.toggle('mark')

    updateExtensionNumbers(extensionNumber)
}

function updateExtensionNumbers(extensionNumber) {
    if (localStorage.extensionNumbers) {
        const extensionNumbers = loadExtensionNumbers()
        const index = extensionNumbers.findIndex(ext => ext === extensionNumber)

        if (index === -1) {
            extensionNumbers.push(extensionNumber)
        } else {
            extensionNumbers.splice(index, 1)
        }

        addExtensionNumber(extensionNumbers);

        return
    }

    addExtensionNumber([extensionNumber]);
}

function addExtensionNumber(extensionNumber) {
    localStorage.extensionNumbers = JSON.stringify(extensionNumber);
}

function loadExtensionNumbers() {
    if (localStorage.extensionNumbers) {
        return JSON.parse(localStorage.extensionNumbers);
    }
}

function highlight() {
    const extensionNumbers = loadExtensionNumbers();

    if (extensionNumbers) {
        extensionNumbers.forEach(extensionNumber => {
            document.querySelector(`#pb_${extensionNumber}`).classList.add('mark');
        });
    }
}

highlight()
