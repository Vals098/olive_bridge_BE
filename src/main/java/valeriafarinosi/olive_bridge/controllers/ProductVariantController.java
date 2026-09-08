package valeriafarinosi.olive_bridge.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valeriafarinosi.olive_bridge.entities.ProductVariant;
import valeriafarinosi.olive_bridge.services.ProductVariantService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @GetMapping("/{productId}/variants")
    public List<ProductVariant> getVariantsByProduct(@PathVariable UUID productId) {
        return productVariantService.findByProductId(productId);
    }
}