$(document).ready(function() {
    const API_URL = "http://localhost:8080/app/api"

    function requestMatches() {
        $.ajax({
            url: `${API_URL}/matches`,
            type: "GET",
            dataType: "json",
            success: function (data) {
                const tbody = $('#matches-table tbody');
                tbody.empty();
                $.each(data, function(index, match) {
                    console.log(match)
                    const row = $('<tr></tr>');
                    row.append($('<td></td>').text(match.player1.name));
                    row.append($('<td></td>').text(match.player2.name));
                    row.append($('<td></td>').text(match.winner.name));
                    tbody.append(row);
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
    //            const error = JSON.parse(jqXHR.responseText);
    //            const toast = $('#api-error-toast');
    //
    //            $(toast).find('.toast-body').text(error.message);
    //            toast.toast("show");
                console.log(errorThrown)
            }
        });
    }

    requestMatches();
});