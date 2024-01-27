package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tipolt.andre.spring.dtos.DataHeaderDTO;
import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.exceptions.PasswordNotCoincideException;
import tipolt.andre.spring.models.NotificationModel;
import tipolt.andre.spring.models.RoleModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private NotificationService notificationService;

    @Transactional(readOnly = true)
    public UserModel findDataUser(UserModel userModel) {
        return userRepository.findById(userModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("User doesn't exists"));
    }

    public void saveUser(UserInsertDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new PasswordNotCoincideException("Password not Coincide");
        }

        UserModel newUser = new UserModel();

        String newPassword = passwordEncoder.encode(userDTO.getPassword());

        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(newPassword);

        UserModel createdUser = userRepository.save(newUser);

        RoleModel commomRole = userRoleService.getRoleUser();

        userRoleService.insertManyUserRoles(List.of(commomRole), createdUser); // Only operator user yet
    }

    @Transactional(readOnly = true)
    public UserModel findUserById(String userId) {

        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User Not Found"));
    }

    public void updateUser(String userId, UserUpdateDTO userUpdateDTO) {

        UserModel user = this.findUserById(userId);

        if (!userUpdateDTO.getPassword().equals(userUpdateDTO.getConfirmPassword())) {
            throw new PasswordNotCoincideException("Password not coincides");
        }
        updateUserDtoToUserDatabase(user, userUpdateDTO);

        userRepository.save(user);
    }

    private void updateUserDtoToUserDatabase(UserModel userModel, UserUpdateDTO userUpdateDTO) {
        userModel.setEmail(userUpdateDTO.getEmail());
        userModel.setName(userUpdateDTO.getName());
        userModel.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
    }

    @Transactional(readOnly = true)
    public DataHeaderDTO getDataHeader() {

        List<NotificationModel> listNotifications = notificationService.findAllNotificationsByUser();

        String imageUrl = ""; // Implements

        return new DataHeaderDTO(listNotifications, imageUrl);
    }

    @Transactional(readOnly = true)
    public UserModel findUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Email that not exists in database"));
    }

    public void changePassword(String newPassword, String userId){

        UserModel currentUser = this.findUserById(userId);

        String passwordEncoded = passwordEncoder.encode(newPassword);

        currentUser.setPassword(passwordEncoded);

        userRepository.save(currentUser);

        return;

    }
}
