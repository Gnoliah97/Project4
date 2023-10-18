package com.sem4project.sem4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserInfo extends BaseEntity{
    private String fullName;
    private String phone;
    private String email;
    private Instant dob;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "province_id")
    private Province province;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")
    private District district;
    private String address;
    @OneToOne(mappedBy = "userInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}
