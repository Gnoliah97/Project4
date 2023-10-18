package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class UserInfoDto{
    @Length(min = 8)
    private String fullName;
    private String phone;
    @Email
    private String email;
    private Instant dob;
//    private ProvinceDto province;
//    private DistrictDto district;
    private String address;
}
