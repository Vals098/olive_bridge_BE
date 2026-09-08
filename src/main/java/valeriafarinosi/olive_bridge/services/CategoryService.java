package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.Category;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.CategoryRequestDTO;
import valeriafarinosi.olive_bridge.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CategoryRequestDTO body) {

        Category category = new Category(
                body.name(),
                body.description(),
                body.status()
        );

        return categoryRepository.save(category);
    }
}