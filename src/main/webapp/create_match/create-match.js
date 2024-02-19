$(document).ready(function() {
    const API_URL = "http://localhost:8080/app/api";

    $("#create-match-form").submit(function(e) {
        e.preventDefault();

        $.ajax({
            url: `${API_URL}/new-match`,
            type: "POST",
            data: $("#create-match-form").serialize(),
            success: function(data, textStatus, request) {
                json = JSON.parse(data)
                window.location.replace('http://localhost:8080/app/match-score?uuid=' + json.id)
            },
            error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
//                console.log(jqXHR);
//                console.log(textStatus);
//                console.log(errorThrown);
//                const error = JSON.parse(jqXHR.responseText);
//                const toast = $('#api-error-toast');
//
//                $(toast).find('.toast-body').text(error.message);
//                toast.toast("show");
            }
        });

        return false;
    });
});