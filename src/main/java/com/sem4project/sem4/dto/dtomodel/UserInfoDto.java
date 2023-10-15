package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoDto{
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private Instant dob;
    private ProvinceDto province;
    private DistrictDto district;
    private String address;
    private UserDto user;
}
