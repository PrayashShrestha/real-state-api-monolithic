package miu.ea.realestateapimonolithic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;
import miu.ea.realestateapimonolithic.model.Role;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String name;
    private String tel;
    private String location;
}
