package valeriafarinosi.olive_bridge.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.OrderResponseDTO;
import valeriafarinosi.olive_bridge.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/users/me/orders")
public class UserOrderController {

    private final OrderService orderService;

    public UserOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponseDTO> getMyOrders(
            Authentication authentication
    ) {

        User currentUser = (User) authentication.getPrincipal();

        return orderService.getOrdersByUser(currentUser);
    }
}