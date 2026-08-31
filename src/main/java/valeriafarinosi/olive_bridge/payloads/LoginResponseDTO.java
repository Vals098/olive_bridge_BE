package valeriafarinosi.olive_bridge.payloads;

public record LoginResponseDTO(
        String accessToken,
        UserResponseDTO user
) {
}