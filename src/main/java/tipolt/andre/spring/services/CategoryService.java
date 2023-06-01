package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.repositories.CategoryRepository;
import tipolt.andre.spring.repositories.ProductRepository;
import tipolt.andre.spring.services.exceptions.ObjectNotFoundException;

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
}
