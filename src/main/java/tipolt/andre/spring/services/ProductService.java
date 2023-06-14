package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.dtos.ProductDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.CategoryModel;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.repositories.CategoryRepository;
import tipolt.andre.spring.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public Page<ProductModel> findAllPaged(Pageable pageable) {
        Page<ProductModel> listProduct = productRepository.findAll(pageable);
        return listProduct;
    }

    public ProductModel findProductById(String productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ObjectNotFoundException("Product Not Found"));
    }

    public void saveProduct(ProductDTO newProduct){

        CategoryModel category = categoryRepository.findById(newProduct.getCategoryId())
                                    .orElseThrow(() -> new ObjectNotFoundException("Category Not Found"));

        ProductModel product = new ProductModel();

        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setCategory(category);

        productRepository.save(product);
    }
}
