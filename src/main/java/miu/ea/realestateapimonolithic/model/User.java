package miu.ea.realestateapimonolithic.model;

import jakarta.persistence.*;
import lombok.*;
import miu.ea.realestateapimonolithic.common.UserStatusEnum;

import java.time.LocalDateTime;

@Entity
@Data
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Boolean profileInReview;

    private int failedAttempt;
    private LocalDateTime lockTime;
}
