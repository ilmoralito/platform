//= require jquery/jquery
//= require bootstrap
//= require bootstrap-datepicker/bootstrap-datepicker
//= require_self

(function() {
    const triggers = document.querySelectorAll('.triggers')

    triggers.forEach((trigger) => {
        trigger.addEventListener('click', toggleTelephoneNumber, false);
    });

    function toggleTelephoneNumber(event) {
        const id = parseInt(event.target.id, 10);
        const indices = loadData()

        if (indices.length) {
            const index = indices.indexOf(id);

            if (index > -1) {
                indices.splice(index, 1);

                localStorage.setItem('indices', JSON.stringify(indices));
                removeHighlight(id);
                return
            }
        }


        indices.push(id);
        localStorage.setItem('indices', JSON.stringify(indices));
        addHighlight(id);
    }

    function loadData() {
        if (localStorage.indices) {
            return JSON.parse(localStorage.indices)
        }

        return []
    }


    function highlight() {
        const indices = loadData()

        indices.forEach((index) => {
            document.getElementById('pb_' + index).classList.add('mark');
        });
    }

    function addHighlight(index) {
        document.getElementById('pb_' + index).classList.add('mark');
    }

    function removeHighlight(index) {
        document.getElementById('pb_' + index).classList.remove('mark');
    }

    highlight();
})();
