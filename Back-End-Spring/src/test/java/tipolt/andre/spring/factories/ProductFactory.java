package tipolt.andre.spring.factories;

import tipolt.andre.spring.dtos.ProductDTO;

public class ProductFactory {

    // public static ProductDTO createProductDTO() {
    //     ProductDTO productDTO = new ProductDTO("Phone", 999.99, 1L);
    //     return productDTO;
    // }

    public static ProductDTO createProductDTOMissingField() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Phone");
        productDTO.setCategoryId(1L);
        return productDTO;
    }
}
