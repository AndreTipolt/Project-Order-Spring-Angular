package tipolt.andre.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.validation.Valid;
import tipolt.andre.spring.controllers.utils.ObjectMapperUtils;
import tipolt.andre.spring.dtos.ProductDTO;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.services.ProductService;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    @GetMapping
    public ResponseEntity<? extends Object> findAll()
            throws JsonMappingException, JsonProcessingException {

        // JsonNode findAllProductCached = objectMapperUtils.getRedisKeyAndConvertToJsonNode("products_findAll");
        // if (findAllProductCached != null) {
        //     return ResponseEntity.ok().body(findAllProductCached);
        // }

        List<ProductModel> listProducts = productService.findAll();

        // objectMapperUtils.convertObjectToStringAndSaveInRedis("products_findAll", listProducts);

        return ResponseEntity.ok().body(listProducts);
    }
    // @GetMapping
    // public ResponseEntity<? extends Object> findAll(Pageable pageable)
    //         throws JsonMappingException, JsonProcessingException {

    //     // JsonNode findAllProductCached = objectMapperUtils.getRedisKeyAndConvertToJsonNode("products_findAll");
    //     // if (findAllProductCached != null) {
    //     //     return ResponseEntity.ok().body(findAllProductCached);
    //     // }

    //     Page<ProductModel> listProducts = productService.findAllPaged(pageable);

    //     // objectMapperUtils.convertObjectToStringAndSaveInRedis("products_findAll", listProducts);

    //     return ResponseEntity.ok().body(listProducts);
    // }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductModel> findProductById(@PathVariable String productId) {
        ProductModel product = productService.findProductById(productId);

        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody @Valid ProductDTO productDTO) {

        productService.saveProduct(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductDTO productDTO,
            @PathVariable String productId) {

        productService.updateProduct(productDTO, productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductModel>> getMethodName(@RequestParam String q) {
        
        List<ProductModel> listProduct = productService.searchProduct(q);

        return ResponseEntity.ok().body(listProduct);
    }
    
}
