const API_URL = "http://localhost:8080/app/api"
let page = 1;
let pageSize = 10;
let totalPages;

$(document).ready(function() {
    $("body").append($("<div id=\"pageable\" style=\"display: flex;flex-direction: row;\"></div>").html(
         "<input id=\"select-page-size\" style=\"width: 25px;\"type=\"text\" value=\""+pageSize+"\" name=\"pageSize\">"+
         "<button id=\"prev-page\" type=\"submit\"> < </button>"+
         "<div id=\"page\">" + page + "</div>"+
         "<button id=\"next-page\" type=\"submit\"> > </button>"+
         "<div id=\"total-pages\">Из "+totalPages+"</div>"
    ))

    $('#select-page-size').on('input', function() {
        pageSize = $(this).val();
    });

    requestMatches(page, pageSize);

    $("#next-page").click(function() {
        nextPage()
    })
    $("#prev-page").click(function() {
        prevPage()
    })
});

function requestMatches(page, size) {
        $.ajax({
            url: `${API_URL}/matches?page=`+page+`&pageSize=`+size,
            type: "GET",
            dataType: "json",
            success: function (data) {
                const tbody = $('#matches-table tbody');
                tbody.empty();
                totalPages = data.totalPages;
                $("#total-pages").html("Из " + totalPages);
                $.each(data.elements, function(index, match) {
                    const row = $('<tr></tr>');
                    row.append($('<td></td>').text(match.player1.name));
                    row.append($('<td></td>').text(match.player2.name));
                    row.append($('<td></td>').text(match.winner.name));
                    tbody.append(row);
                });
                $("#page").html(page);
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

function nextPage() {
    if (page < totalPages) {
        page++;
        requestMatches(page, pageSize)
    }
}

function prevPage() {
    if (page > 1) {
        page--;
        requestMatches(page, pageSize)
    }
}