package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.Order;
import valeriafarinosi.olive_bridge.entities.OrderItem;
import valeriafarinosi.olive_bridge.entities.ProductVariant;
import valeriafarinosi.olive_bridge.enums.OrderStatus;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.CheckoutRequestDTO;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.OrderItemRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.OrderResponseDTO;
import valeriafarinosi.olive_bridge.repositories.OrderItemRepository;
import valeriafarinosi.olive_bridge.repositories.OrderRepository;
import valeriafarinosi.olive_bridge.repositories.ProductVariantRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductVariantRepository productVariantRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        ProductVariantRepository productVariantRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productVariantRepository = productVariantRepository;
    }

    public OrderResponseDTO createOrder(CheckoutRequestDTO payload) {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequestDTO item : payload.items()) {

            ProductVariant variant = productVariantRepository.findById(item.productVariantId())
                    .orElseThrow(() -> new RuntimeException("Product variant not found."));

            BigDecimal subtotal = variant.getPrice()
                    .multiply(BigDecimal.valueOf(item.quantity()));

            total = total.add(subtotal);
        }

        Order order = new Order(
                null,
                payload.customerEmail(),
                LocalDateTime.now(),
                total,
                OrderStatus.PENDING,
                payload.customerName(),
                payload.shippingPostalCode(),
                payload.shippingPrefecture(),
                payload.shippingCity(),
                payload.shippingArea(),
                payload.shippingStreet(),
                payload.shippingBuilding()
        );
        orderRepository.save(order);

        for (OrderItemRequestDTO item : payload.items()) {

            ProductVariant variant = productVariantRepository.findById(item.productVariantId())
                    .orElseThrow(() -> new RuntimeException("Product variant not found."));

            OrderItem orderItem = new OrderItem(
                    item.quantity(),
                    variant.getPrice(),
                    order,
                    variant
            );

            orderItemRepository.save(orderItem);
        }

        return new OrderResponseDTO(
                order.getOrderId(),
                order.getCustomerEmail(),
                order.getOrderDate(),
                order.getTotal(),
                order.getStatus(),
                order.getShippingRecipientName(),
                order.getShippingPostalCode(),
                order.getShippingPrefecture(),
                order.getShippingCity(),
                order.getShippingArea(),
                order.getShippingStreet(),
                order.getShippingBuilding()
        );
    }
}