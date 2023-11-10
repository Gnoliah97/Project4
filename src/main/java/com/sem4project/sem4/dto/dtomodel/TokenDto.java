package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDto {
    private String jwtToken;
    private Date expiresIn;
    private String role;
}
