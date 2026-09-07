package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.entities.Category;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.CategoryRequestDTO;
import valeriafarinosi.olive_bridge.services.CategoryService;

@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category createCategory(
            @Valid @RequestBody CategoryRequestDTO body
    ) {
        return categoryService.createCategory(body);
    }
}