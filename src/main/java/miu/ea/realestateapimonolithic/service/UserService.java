package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.*;
import miu.ea.realestateapimonolithic.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    void saveUser(AccountRegistrationRequest user);
    void saveAdmin(AccountRegistrationRequest user);

    void save(User user);
    User findUser(long id);
    List<UserResponseDto> findAllUsers();
    void updateUser(long id, UserUpdateDto user, MultipartFile profilePhoto);

    void updatePassword(long id, String oldPassword, String newPassword);
    void activateUser(long id);
    void deactivateUser(long id);

    ApiResponse<?> login(LoginRequest req);
    void approveProfile(long id);
}
