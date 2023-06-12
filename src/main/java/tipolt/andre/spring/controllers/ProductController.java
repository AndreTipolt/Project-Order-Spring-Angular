package tipolt.andre.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductModel>> findAll(Pageable pageable){

        Page<ProductModel> listProducts = productService.findAllPaged(pageable);
        return ResponseEntity.ok().body(listProducts);
    }
}
