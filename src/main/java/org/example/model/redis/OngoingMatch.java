package org.example.model.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.jpa.Player;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OngoingMatch {
    private Long id;
    private Player player1;
    private Player player2;
    private Player winner;
}
