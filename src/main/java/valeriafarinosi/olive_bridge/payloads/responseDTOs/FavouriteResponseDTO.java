package valeriafarinosi.olive_bridge.payloads.responseDTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record FavouriteResponseDTO(
        UUID favouriteId,
        UUID productId,
        LocalDateTime createdAt
) {
}