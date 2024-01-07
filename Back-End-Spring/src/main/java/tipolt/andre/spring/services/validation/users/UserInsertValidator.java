package tipolt.andre.spring.services.validation.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tipolt.andre.spring.controllers.exceptions.FieldMessage;
import tipolt.andre.spring.dtos.UserDTO;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserDTO> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Optional<UserModel> user = userRepository.findByEmail(dto.getEmail());

		if (user.isPresent()) {
			list.add(new FieldMessage("email", "Email already exists"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
