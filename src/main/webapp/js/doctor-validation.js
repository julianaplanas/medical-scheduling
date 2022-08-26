$(document).ready(function () {
    $("form[name='doctorForm']").validate({

        rules : {
            firstName : {
                required : true,
                minlength: 2,
            },
            lastName : {
                required : true,
                minlength: 3,
            },
            email : {
                required : true,
            },
            address : {
                required : true,
            },
            phoneNumber : {
                required : true,
            },
            hourCost : {
                required : true,
            },

        },
        messages : {
            firstName: {
                minlength: "First Name too short, make it at least 2 characters",
            },
            lastName : {
                minlength: "Last Name too short, make it at least 3 characters",
            }
        },
        errorClass : "error"
    });
});