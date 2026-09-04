package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.ProductVariant;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.CheckoutRequestDTO;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.OrderItemRequestDTO;
import valeriafarinosi.olive_bridge.repositories.OrderItemRepository;
import valeriafarinosi.olive_bridge.repositories.OrderRepository;
import valeriafarinosi.olive_bridge.repositories.ProductVariantRepository;

import java.math.BigDecimal;

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

    public void createOrder(CheckoutRequestDTO payload) {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequestDTO item : payload.items()) {

            ProductVariant variant = productVariantRepository.findById(item.productVariantId())
                    .orElseThrow(() -> new RuntimeException("Product variant not found."));

            BigDecimal subtotal = variant.getPrice()
                    .multiply(BigDecimal.valueOf(item.quantity()));

            total = total.add(subtotal);
        }

        System.out.println("Order total: " + total);
    }

}