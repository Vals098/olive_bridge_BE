package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.entities.Product;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.ProductRequestDTO;
import valeriafarinosi.olive_bridge.services.ProductService;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product createProduct(
            @Valid @RequestBody ProductRequestDTO body
    ) {
        return productService.createProduct(body);
    }
}