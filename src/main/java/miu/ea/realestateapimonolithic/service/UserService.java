package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.dto.ApiResponse;
import miu.ea.realestateapimonolithic.dto.LoginRequest;
import miu.ea.realestateapimonolithic.dto.TokenResponse;
import miu.ea.realestateapimonolithic.dto.UserResponseDto;
import miu.ea.realestateapimonolithic.model.User;

import java.util.List;

public interface UserService {

    void saveUser(AccountRegistrationRequest user);
    void saveAdmin(AccountRegistrationRequest user);
    List<UserResponseDto> findAllUsers();
    void updateUser(long id, User user);

    void updatePassword(long id, String oldPassword, String newPassword);
    void activateUser(long id);
    void deactivateUser(long id);

    ApiResponse<?> login(LoginRequest req);
    void approveProfile(long id);
}
