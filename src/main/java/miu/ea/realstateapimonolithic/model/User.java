package miu.ea.realstateapimonolithic.model;

import jakarta.persistence.*;
import lombok.*;
import miu.ea.realstateapimonolithic.common.UserStatusEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @OneToOne
    @PrimaryKeyJoinColumn
    private AgentDetail agentDetail;

    @OneToOne
    @PrimaryKeyJoinColumn
    private BuyerPreference buyerPreference;
}
