package tipolt.andre.spring.services.validation.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tipolt.andre.spring.controllers.exceptions.FieldMessage;
import tipolt.andre.spring.dtos.UserUpdateDTO;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.UserRepository;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
        String userIdRequest = uriVars.get("userId");

		List<FieldMessage> list = new ArrayList<>();

        Optional<UserModel> user = userRepository.findByEmail(dto.getEmail());

        if(user.isPresent() && !user.get().getId().equals(userIdRequest)){
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
