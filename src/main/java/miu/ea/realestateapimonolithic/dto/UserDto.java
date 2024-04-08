package miu.ea.realestateapimonolithic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;
import miu.ea.realestateapimonolithic.common.RoleEnum;
import miu.ea.realestateapimonolithic.model.Role;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private long id;

    private String email;
    private String password;
    private RoleEnum userRole;
    private UserStatusEnum status;
}
