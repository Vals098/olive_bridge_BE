package valeriafarinosi.olive_bridge.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.CheckoutRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.OrderResponseDTO;
import valeriafarinosi.olive_bridge.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public OrderResponseDTO createOrder(@RequestBody CheckoutRequestDTO payload) {
        return orderService.createOrder(payload);
    }
}