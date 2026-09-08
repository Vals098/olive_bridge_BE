package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.Order;
import valeriafarinosi.olive_bridge.entities.OrderItem;
import valeriafarinosi.olive_bridge.entities.ProductVariant;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.enums.OrderStatus;
import valeriafarinosi.olive_bridge.enums.PaymentStatus;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.CheckoutRequestDTO;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.OrderItemRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.OrderResponseDTO;
import valeriafarinosi.olive_bridge.repositories.OrderItemRepository;
import valeriafarinosi.olive_bridge.repositories.OrderRepository;
import valeriafarinosi.olive_bridge.repositories.ProductVariantRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductVariantRepository productVariantRepository;
    private final MailgunService mailgunService;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductVariantRepository productVariantRepository, MailgunService mailgunService
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productVariantRepository = productVariantRepository;
        this.mailgunService = mailgunService;
    }

    public OrderResponseDTO createOrder(
            CheckoutRequestDTO payload,
            User currentUser
    ) {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequestDTO item : payload.items()) {

            ProductVariant variant = productVariantRepository.findById(
                    item.productVariantId()
            ).orElseThrow(() ->
                    new RuntimeException("Product variant not found.")
            );

            BigDecimal subtotal = variant.getPrice()
                    .multiply(BigDecimal.valueOf(item.quantity()));

            total = total.add(subtotal);
        }

        Order order = new Order(
                currentUser,
                payload.customerEmail(),
                LocalDateTime.now(),
                total,
                OrderStatus.PENDING,

                // PAYMENT
                payload.paymentMethod(),
                PaymentStatus.PAID,

                // SHIPPING
                payload.customerName(),
                payload.shippingPostalCode(),
                payload.shippingPrefecture(),
                payload.shippingCity(),
                payload.shippingArea(),
                payload.shippingStreet(),
                payload.shippingBuilding(),

                // BILLING
                payload.billingPostalCode(),
                payload.billingPrefecture(),
                payload.billingCity(),
                payload.billingArea(),
                payload.billingStreet(),
                payload.billingBuilding()
        );

        orderRepository.save(order);

        for (OrderItemRequestDTO item : payload.items()) {

            ProductVariant variant = productVariantRepository.findById(
                    item.productVariantId()
            ).orElseThrow(() ->
                    new RuntimeException("Product variant not found.")
            );

            OrderItem orderItem = new OrderItem(
                    item.quantity(),
                    variant.getPrice(),
                    order,
                    variant
            );

            orderItemRepository.save(orderItem);
        }

        mailgunService.sendOrderConfirmation(
                order.getCustomerEmail(),
                order.getOrderId().toString(),
                order.getTotal().toString()
        );

        return new OrderResponseDTO(
                order.getOrderId(),
                order.getCustomerEmail(),
                order.getOrderDate(),
                order.getTotal(),
                order.getStatus(),

                // PAYMENT
                order.getPaymentMethod(),
                order.getPaymentStatus(),

                // SHIPPING
                order.getShippingRecipientName(),
                order.getShippingPostalCode(),
                order.getShippingPrefecture(),
                order.getShippingCity(),
                order.getShippingArea(),
                order.getShippingStreet(),
                order.getShippingBuilding(),

                // BILLING
                order.getBillingPostalCode(),
                order.getBillingPrefecture(),
                order.getBillingCity(),
                order.getBillingArea(),
                order.getBillingStreet(),
                order.getBillingBuilding()
        );
    }

    public List<OrderResponseDTO> getOrdersByUser(User currentUser) {

        return orderRepository
                .findByUserOrderByOrderDateDesc(currentUser)
                .stream()
                .map(order -> new OrderResponseDTO(
                        order.getOrderId(),
                        order.getCustomerEmail(),
                        order.getOrderDate(),
                        order.getTotal(),
                        order.getStatus(),

                        // PAYMENT
                        order.getPaymentMethod(),
                        order.getPaymentStatus(),

                        // SHIPPING
                        order.getShippingRecipientName(),
                        order.getShippingPostalCode(),
                        order.getShippingPrefecture(),
                        order.getShippingCity(),
                        order.getShippingArea(),
                        order.getShippingStreet(),
                        order.getShippingBuilding(),

                        // BILLING
                        order.getBillingPostalCode(),
                        order.getBillingPrefecture(),
                        order.getBillingCity(),
                        order.getBillingArea(),
                        order.getBillingStreet(),
                        order.getBillingBuilding()
                ))
                .toList();
    }

    public List<OrderResponseDTO> getAllOrders() {

        return orderRepository
                .findAll()
                .stream()
                .map(order -> new OrderResponseDTO(
                        order.getOrderId(),
                        order.getCustomerEmail(),
                        order.getOrderDate(),
                        order.getTotal(),
                        order.getStatus(),

                        // PAYMENT
                        order.getPaymentMethod(),
                        order.getPaymentStatus(),

                        // SHIPPING
                        order.getShippingRecipientName(),
                        order.getShippingPostalCode(),
                        order.getShippingPrefecture(),
                        order.getShippingCity(),
                        order.getShippingArea(),
                        order.getShippingStreet(),
                        order.getShippingBuilding(),

                        // BILLING
                        order.getBillingPostalCode(),
                        order.getBillingPrefecture(),
                        order.getBillingCity(),
                        order.getBillingArea(),
                        order.getBillingStreet(),
                        order.getBillingBuilding()
                ))
                .toList();
    }
}