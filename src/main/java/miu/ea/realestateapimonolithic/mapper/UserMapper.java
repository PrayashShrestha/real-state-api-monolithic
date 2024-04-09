package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    AccountRegistrationRequest mapToUserDto(User user);
    User mapToUser(AccountRegistrationRequest accountRegistrationRequest);
}
