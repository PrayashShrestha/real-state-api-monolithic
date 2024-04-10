package miu.ea.realestateapimonolithic.mapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import miu.ea.realestateapimonolithic.dto.AccountRegistrationRequest;
import miu.ea.realestateapimonolithic.dto.UserResponseDto;
import miu.ea.realestateapimonolithic.dto.UserUpdateDto;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final UserRepository userRepository;

    public UserResponseDto childTypeMapToUser(User user){
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        responseDto.setName(user.getName());
        responseDto.setTel(user.getTel());
        responseDto.setLocation(user.getLocation());
        // Check if the Role is initialized, if not, load it eagerly
        if (user.getRole() != null && !Hibernate.isInitialized(user.getRole())) {
            user.setRole(userRepository.getOne(user.getId()).getRole());
        }
        responseDto.setRole(user.getRole());
        responseDto.setStatus(user.getStatus());
        return responseDto;
    }
    public static User mapToUser(AccountRegistrationRequest accountRegistrationRequest){
        User user = new User();
        BeanUtils.copyProperties(accountRegistrationRequest, user);
        return user;
    }
}
