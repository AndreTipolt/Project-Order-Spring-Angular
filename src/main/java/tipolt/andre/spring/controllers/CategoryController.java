package tipolt.andre.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tipolt.andre.spring.models.CategoryModel;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{categoryId}")
    public List<ProductModel> findCategoryById(@PathVariable Long categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @GetMapping
    public List<CategoryModel> findAll() {
        return categoryService.findAll();
    }
}
