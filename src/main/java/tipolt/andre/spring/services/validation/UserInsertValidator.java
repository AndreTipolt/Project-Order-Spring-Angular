// package tipolt.andre.spring.services.validation;

// import java.util.ArrayList;
// import java.util.List;

// import jakarta.validation.ConstraintValidator;
// import jakarta.validation.ConstraintValidatorContext;
// import tipolt.andre.spring.dtos.UserDTO;

// import org.springframework.beans.factory.annotation.Autowired;

// public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserDTO> {
	
// 	@Override
// 	public void initialize(UserInsertValid ann) {
// 	}

// 	@Override
// 	public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {
		
// 		List<FieldMessage> list = new ArrayList<>();
		
		
// 		for (FieldMessage e : list) {
// 			context.disableDefaultConstraintViolation();
// 			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
// 					.addConstraintViolation();
// 		}
// 		return list.isEmpty();
// 	}
// }
