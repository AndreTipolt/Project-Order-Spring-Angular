package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.repositories.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> findAll(){
        return productRepository.findAll();
    }

    public Page<ProductModel> findAllPaged(Pageable pageable) {
        Page<ProductModel> listProduct = productRepository.findAll(pageable);
        return listProduct;
    }
}
