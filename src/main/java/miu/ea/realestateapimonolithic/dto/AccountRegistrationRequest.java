package miu.ea.realestateapimonolithic.dto;

import lombok.*;
import miu.ea.realestateapimonolithic.common.RoleEnum;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRegistrationRequest {
    private long id;

    private String email;
    private String password;
    private RoleEnum userRole;
}
