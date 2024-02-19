package org.example.model.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayerScore {
    private static final List<String> POSSIBLE_POINTS = List.of("0", "15", "30", "40", "Ad");
    private int games;
    private int sets;
    private int currentPointPtr = 0;
    private boolean isTiebreak = false;
    private int tiebreakPoints = 0;
    private boolean isFinished = false;
    private boolean isWinner = false;
    private String points = POSSIBLE_POINTS.get(currentPointPtr);

    public String getPoints() {
        return POSSIBLE_POINTS.get(currentPointPtr);
    }

    public void increaseScore(PlayerScore other) {
        if (isFinished) {
            return;
        }
        if (isTiebreak) {
            tiebreak(other);
            return;
        }
        if (currentPointPtr < 3) {
            currentPointPtr++;
            return;
        }
        if (currentPointPtr == 3) {
            if (other.getCurrentPointPtr() <= 2) {
                finishGame(other);
                return;
            }
            if (other.getCurrentPointPtr() == 3) {
                currentPointPtr++;
                return;
            }
            if (other.getCurrentPointPtr() == 4) {
                other.setCurrentPointPtr(3);
                return;
            }
        }
        if (currentPointPtr == 4) {
            finishGame(other);
        }
    }

    private void finishGame(PlayerScore other) {
        currentPointPtr = 0;
        other.setCurrentPointPtr(0);
        if (games <= 4) {
            games++;
            return;
        }
        if (games == 5 && other.getGames() <= 4) {
            finishSet(other);
            return;
        }
        if (games == 5 && other.getGames() == 6) {
            games++;
            isTiebreak = true;
            other.setTiebreak(true);
            currentPointPtr = 0;
            other.currentPointPtr = 0;
            return;
        }
        if (games == 6 && other.getGames() == 5) {
            finishSet(other);
            return;
        }
    }

    private void finishSet(PlayerScore other) {
        currentPointPtr = 0;
        isTiebreak = false;
        tiebreakPoints = 0;
        games = 0;
        other.setCurrentPointPtr(0);
        other.setTiebreak(false);
        other.setTiebreakPoints(0);
        other.setGames(0);
        sets++;
        if (sets == 2) {
            isFinished = true;
            isWinner = true;
            other.setWinner(false);
            other.setFinished(true);
        }
    }

    private void tiebreak(PlayerScore other) {
        tiebreakPoints++;
        if (tiebreakPoints >= 7 && other.tiebreakPoints <= tiebreakPoints - 2) {
            finishSet(other);
        }
    }
}
