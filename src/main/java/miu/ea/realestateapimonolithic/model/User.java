package miu.ea.realestateapimonolithic.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;

    private String name;
    private String tel;
    private String location;

    @OneToOne
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    @JsonManagedReference(value = "user-property")
    @OneToMany(mappedBy = "user")
    private List<Property> properties;

}
