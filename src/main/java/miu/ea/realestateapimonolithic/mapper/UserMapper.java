package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;

public class UserMapper {
//    public static AccountRegistrationRequest mapToUserDto(User user){
//        return null;
//    }
    public static User mapToUser(AccountRegistrationRequest accountRegistrationRequest){
        User user = new User();
        BeanUtils.copyProperties(accountRegistrationRequest, user);
        return user;
    }
}
