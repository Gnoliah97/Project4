package com.sem4project.sem4.dto.response;

import com.sem4project.sem4.dto.dtomodel.UserDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterResponse {
    private UserDto userDto;
}
