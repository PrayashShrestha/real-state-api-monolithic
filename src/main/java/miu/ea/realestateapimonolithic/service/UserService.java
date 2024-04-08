package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.dto.LoginRequest;
import miu.ea.realestateapimonolithic.dto.TokenResponse;
import miu.ea.realestateapimonolithic.model.User;

import java.util.List;

public interface UserService {

    void saveUser(AccountRegistrationRequest user);
    User findUser(long id);
    List<User> findAllUsers();
    User updateUser(long id, User user);
    void deleteUser(long id);

    void updatePassword(long id, String oldPassword, String newPassword);
    void activateUser(long id);
    void deactivateUser(long id);

    TokenResponse login(LoginRequest req);
}
