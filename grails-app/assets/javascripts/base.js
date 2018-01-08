//= require jquery/jquery
//= require bootstrap/js/bootstrap
//= require bootstrap-datepicker/js/bootstrap-datepicker
//= require_self

$('#sinceDate, #tillDate').datepicker({
    autoclose: true,
    format: 'yyyy-mm-dd'
});
