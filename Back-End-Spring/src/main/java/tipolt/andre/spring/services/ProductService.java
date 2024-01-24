package tipolt.andre.spring.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tipolt.andre.spring.dtos.ProductDTO;
import tipolt.andre.spring.dtos.mappers.ProductMapper;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.CategoryModel;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.repositories.CategoryRepository;
import tipolt.andre.spring.repositories.ProductRepository;

@Service
public class ProductService implements Serializable{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<ProductModel> findAllPaged(Pageable pageable) {
        Page<ProductModel> listProduct = productRepository.findAll(pageable);
        return listProduct;
    }

    @Transactional(readOnly = true)
    public ProductModel findProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product Not Found"));
    }

    @Transactional
    public void saveProduct(ProductDTO newProduct) {

        ProductModel productModel =  productMapper.convertToProductModel(newProduct);

        productRepository.save(productModel);

    }

    public void updateProduct(ProductDTO productDTO, String productId) {

        ProductModel product = this.findProductById(productId);

        ProductModel productModel = this.update(productDTO, product);

        productRepository.save(productModel);
    }

    @Transactional(readOnly = true)
    public List<ProductModel> searchProduct(String nameProduct){

        List<ProductModel> searchedProducts = productRepository.findAllByNameStartsWithIgnoreCase(nameProduct);

        return searchedProducts;
    }

    private ProductModel update(ProductDTO productDTO, ProductModel product) {

        CategoryModel category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ObjectNotFoundException("Invalid Category Id"));

        product.setName(productDTO.getName());
        product.setFowardPrice(productDTO.getFowardPrice());
        product.setSpotPrice(productDTO.getSpotPrice());
        product.setDescription(productDTO.getDescription());
        product.setInstallments(productDTO.getInstallments());
        product.setPixDiscount(productDTO.getPixDiscount());
        product.setCategory(category);

        return product;
    }
}
