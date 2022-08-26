function validateAppointment() {
    const timeSlotSelectedf = $('.radio:checked').length;
    const hasTime = timeSlotSelectedf > 0;

    if(hasTime) {
        $("#confirmMessage").show();
        $("#confirmButton").show();
        $("#errorMessage").hide();
    } else {
        $("#confirmMessage").hide();
        $("#confirmButton").hide();
        $("#errorMessage").show();
    }
}

$(document).ready(function () {
    $("#makeAppointmentButton").click(validateAppointment);
});