package miu.ea.realestateapimonolithic.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
