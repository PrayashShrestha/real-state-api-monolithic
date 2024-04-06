package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.model.User;

public interface UserService {

    void saveUser(UserDto user);
}
