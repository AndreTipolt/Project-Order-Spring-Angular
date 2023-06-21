package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.CategoryModel;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.repositories.CategoryRepository;
import tipolt.andre.spring.repositories.ProductRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> findCategoryById(String categoryId){

        categoryRepository.findById(categoryId).orElseThrow(() -> new ObjectNotFoundException("Category Not Found"));

        List<ProductModel> listProductsByCategory = productRepository.findByCategoryId(categoryId);
        return listProductsByCategory;
    }

    public List<CategoryModel> findAll(){
        return categoryRepository.findAll();
    }
}
