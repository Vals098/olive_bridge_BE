package valeriafarinosi.olive_bridge.payloads.responseDTOs;

import valeriafarinosi.olive_bridge.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponseDTO(
        UUID orderId,
        String customerEmail,
        LocalDateTime orderDate,
        BigDecimal total,
        OrderStatus status,
        String shippingRecipientName,
        String shippingPostalCode,
        String shippingPrefecture,
        String shippingCity,
        String shippingArea,
        String shippingStreet,
        String shippingBuilding
) {
}