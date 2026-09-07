package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.Category;
import valeriafarinosi.olive_bridge.entities.Product;
import valeriafarinosi.olive_bridge.entities.TechnicalInformation;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;
import valeriafarinosi.olive_bridge.exceptions.BadRequestException;
import valeriafarinosi.olive_bridge.exceptions.NotFoundException;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.ProductRequestDTO;
import valeriafarinosi.olive_bridge.repositories.CategoryRepository;
import valeriafarinosi.olive_bridge.repositories.ProductRepository;
import valeriafarinosi.olive_bridge.repositories.TechnicalInformationRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TechnicalInformationRepository technicalInformationRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, TechnicalInformationRepository technicalInformationRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.technicalInformationRepository = technicalInformationRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public Product createProduct(ProductRequestDTO body) {

        Category category = categoryRepository.findById(body.categoryId())
                .orElseThrow(() ->
                        new NotFoundException("Category not found.")
                );

        TechnicalInformation technicalInformation =
                technicalInformationRepository
                        .findById(body.technicalInformationId())
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Technical information not found."
                                )
                        );

        Product product = new Product(
                body.name(),
                body.description(),
                body.image(),
                body.status(),
                category,
                technicalInformation
        );

        return productRepository.save(product);
    }

    public Product updateProduct(UUID productId, ProductRequestDTO body) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new NotFoundException("Product not found.")
                );

        Category category = categoryRepository.findById(body.categoryId())
                .orElseThrow(() ->
                        new NotFoundException("Category not found.")
                );

        TechnicalInformation technicalInformation =
                technicalInformationRepository
                        .findById(body.technicalInformationId())
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Technical information not found."
                                )
                        );

        product.update(
                body.name(),
                body.description(),
                body.image(),
                body.status(),
                category,
                technicalInformation
        );

        return productRepository.save(product);
    }

    public Product deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found."));

        if (product.getStatus() == ActiveStatus.INACTIVE) {
            throw new BadRequestException("Product is already inactive.");
        }

        product.deactivate();

        return productRepository.save(product);
    }

    public Product activateProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found."));

        if (product.getStatus() == ActiveStatus.ACTIVE) {
            throw new BadRequestException("Product is already active.");
        }

        product.activate();

        return productRepository.save(product);
    }
}