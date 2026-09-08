package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.entities.Product;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.ProductRequestDTO;
import valeriafarinosi.olive_bridge.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product getProductById(@PathVariable UUID productId) {
        return productService.findById(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct(
            @Valid @RequestBody ProductRequestDTO body
    ) {
        return productService.createProduct(body);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product updateProduct(
            @PathVariable UUID productId,
            @Valid @RequestBody ProductRequestDTO body
    ) {
        return productService.updateProduct(productId, body);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product deleteProduct(@PathVariable UUID productId) {
        return productService.deleteProduct(productId);
    }

    @PatchMapping("/{productId}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public Product activateProduct(@PathVariable UUID productId) {
        return productService.activateProduct(productId);
    }
}