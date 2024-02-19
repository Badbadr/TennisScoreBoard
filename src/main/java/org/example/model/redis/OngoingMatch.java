package org.example.model.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.jpa.Player;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OngoingMatch {
    private UUID id;
    private Player player1;
    private Player player2;
    private PlayerScore playerScore1;
    private PlayerScore playerScore2;
    private Player winner;

    public void updateScore(boolean isFirstWin) {
        if (winner != null) {
            return;
        }
        if (isFirstWin) {
            playerScore1.increaseScore(playerScore2);
        } else {
            playerScore2.increaseScore(playerScore1);
        }

        if (playerScore1.isWinner()) {
            winner = player1;
        }
        if (playerScore2.isWinner()) {
            winner = player2;
        }
    }
}
