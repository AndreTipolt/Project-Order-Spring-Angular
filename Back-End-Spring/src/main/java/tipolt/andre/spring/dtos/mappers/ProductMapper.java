package tipolt.andre.spring.dtos.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tipolt.andre.spring.dtos.ProductDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.CategoryModel;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.repositories.CategoryRepository;

@Component
public class ProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO convertToProductDTO(ProductModel productModel) {

        ProductDTO productDTO = new ProductDTO();

        productDTO.setInstallments(productModel.getInstallments());
        productDTO.setName(productModel.getName());
        productDTO.setPixDiscount(productModel.getPixDiscount());
        productDTO.setSpotPrice(productModel.getSpotPrice());
        productDTO.setDescription(productModel.getDescription());
        productDTO.setCategoryId(productModel.getCategory().getId());
        productDTO.setInstallments(productModel.getInstallments());
        return productDTO;
    }

    public ProductModel convertToProductModel(ProductDTO productDTO) {

        CategoryModel category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ObjectNotFoundException("Category Not Found"));

        ProductModel productModel = new ProductModel();

        productModel.setName(productDTO.getName());
        productModel.setFowardPrice(productDTO.getFowardPrice());
        productModel.setSpotPrice(productDTO.getSpotPrice());
        productModel.setDescription(productDTO.getDescription());
        productModel.setInstallments(productDTO.getInstallments());
        productModel.setPixDiscount(productDTO.getPixDiscount());
        productModel.setCategory(category);

        return productModel;
    }
}
