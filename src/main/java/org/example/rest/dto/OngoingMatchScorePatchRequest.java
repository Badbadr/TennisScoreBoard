package org.example.rest.dto;

import java.util.UUID;

public record OngoingMatchScorePatchRequest(
    UUID matchId,
    Long winnerId
) {
}
