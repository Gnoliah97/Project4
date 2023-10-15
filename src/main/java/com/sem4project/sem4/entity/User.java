package com.sem4project.sem4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User extends BaseEntity {
    @Column(unique = true, name = "username", length = 30)
    private String username;
    @Column(name = "password", length = 120)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    @OneToOne(fetch = FetchType.LAZY)
    private UserInfo userInfo;

}
