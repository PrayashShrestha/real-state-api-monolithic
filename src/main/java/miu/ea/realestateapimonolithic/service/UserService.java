package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.model.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto user);
    User findUser(long id);
    List<User> findAllUsers();
    User updateUser(long id, User user);
    void deleteUser(long id);

    void activateUser(long id);
    void deactivateUser(long id);
}
