package tipolt.andre.spring.controllers.factories;

import tipolt.andre.spring.dtos.ProductDTO;

public class ProductFactory {
    

    public static ProductDTO createProductDTO() {
        ProductDTO productDTO = new ProductDTO("Phone", 999.99, "1");
        return productDTO;
    }

    public static ProductDTO createProductDTOMissingField(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Phone");
        productDTO.setCategoryId("1");
        return productDTO;
    }
}
