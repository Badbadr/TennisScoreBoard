$(document).ready(function() {
    const API_URL = "http://localhost:8080/app/api";
    let params = (new URL(document.location)).searchParams;

    $("tbody tr:nth-child(1) button").click(function() {
        $.ajax({
            url: `${API_URL}/match-score?uuid=`+params.get('uuid'),
            type: 'POST',
            contentType : "application/x-www-form-urlencoded",
            data: `isFirstWin=true`,
            success: function(data) {
                let json = JSON.parse(data)
                let player1Score = $("tbody tr:nth-child(1)")
                $(player1Score).find("td:nth-child(3)").html(json.playerScore1.points)
                $(player1Score).find("td:nth-child(2)").html(json.playerScore1.games)
                $(player1Score).find("td:nth-child(1)").html(json.playerScore1.sets)
                let player2Score = $("tbody tr:nth-child(2)")
                $(player2Score).find("td:nth-child(3)").html(json.playerScore2.points)
                $(player2Score).find("td:nth-child(2)").html(json.playerScore2.games)
                $(player2Score).find("td:nth-child(1)").html(json.playerScore2.sets)
            },
            error: function() {
            }
        })
    })

    $("tbody tr:nth-child(2) button").click(function() {
        $.ajax({
            url: `${API_URL}/match-score?uuid=`+params.get('uuid'),
            type: 'POST',
            contentType : "application/x-www-form-urlencoded",
            data: `isFirstWin=false`,
            success: function(data) {
                let json = JSON.parse(data)
                let player1Score = $("tbody tr:nth-child(1)")
                $(player1Score).find("td:nth-child(3)").html(json.playerScore1.points)
                $(player1Score).find("td:nth-child(2)").html(json.playerScore1.games)
                $(player1Score).find("td:nth-child(1)").html(json.playerScore1.sets)
                let player2Score = $("tbody tr:nth-child(2)")
                $(player2Score).find("td:nth-child(3)").html(json.playerScore2.points)
                $(player2Score).find("td:nth-child(2)").html(json.playerScore2.games)
                $(player2Score).find("td:nth-child(1)").html(json.playerScore2.sets)
            },
            error: function() {
            }
        })
    })
});