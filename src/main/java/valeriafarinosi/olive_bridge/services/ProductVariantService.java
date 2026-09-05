package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.ProductVariant;
import valeriafarinosi.olive_bridge.repositories.ProductVariantRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;

    public ProductVariantService(ProductVariantRepository productVariantRepository) {
        this.productVariantRepository = productVariantRepository;
    }

    public List<ProductVariant> findByProductId(UUID productId) {
        return productVariantRepository.findByProduct_ProductId(productId);
    }
}