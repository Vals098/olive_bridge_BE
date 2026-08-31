package valeriafarinosi.olive_bridge.payloads.responseDTOs;

public record LoginResponseDTO(
        String accessToken,
        UserResponseDTO user
) {
}