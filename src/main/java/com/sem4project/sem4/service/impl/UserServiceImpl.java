package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.common.RoleEnum;
import com.sem4project.sem4.dto.dtomodel.RoleDto;
import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.entity.Role;
import com.sem4project.sem4.entity.User;
import com.sem4project.sem4.entity.UserDetailsImpl;
import com.sem4project.sem4.entity.UserInfo;
import com.sem4project.sem4.exception.AuthException;
import com.sem4project.sem4.repository.RoleRepository;
import com.sem4project.sem4.repository.UserInfoRepository;
import com.sem4project.sem4.repository.UserRepository;
import com.sem4project.sem4.service.UserService;
import com.sem4project.sem4.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
//@Transactional
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private ModelMapper mapper;

    @Override
    public void login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            throw new AuthException("Username or password incorrect");
        }
    }

    @Override
    public void register(RegisterRequest request) {
        if (!userRepository.existsByUsername(request.getUsername())) {
            String passwordEncoded = passwordEncoder.encode(request.getPassword());
            User userRegister = mapper.map(request, User.class);
            userRegister.setPassword(passwordEncoded);
            List<Role> defaultRoles = new ArrayList<>();
            Role defaultRole = roleRepository.findByName(RoleEnum.ROLE_USER.name());
            defaultRoles.add(defaultRole);
            userRegister.setRoles(defaultRoles);
            userRepository.save(userRegister);
        } else {
            throw new AuthException("Username already exist");
        }
    }

    @Override
    public void logout() {
        try {
            SecurityContextHolder.clearContext();
        } catch (Exception ex) {
            throw new AuthException("Not logged in yet");
        }
    }

    @Override
    public UserDto getUserInfo() {
        try {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userDetails.getUser();
            UserDto userDto = mapper.map(user, UserDto.class);
            userDto.setRoles(user.getRoles().stream().map(role -> mapper.map(role, RoleDto.class)).toList());
            return userDto;
        } catch (Exception ex) {
            throw new AuthException("Not logged in yet");
        }
    }

    @Override
    public UserInfoDto updateUserInfo(UserInfoDto userInfoDto) {
        try {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userDetails.getUser();
            UserInfo userInfo = user.getUserInfo();
            if(userInfo == null){
                user.setUserInfo(mapper.map(userInfoDto, UserInfo.class));
                userRepository.save(user);
            } else{
                mapper.map(userInfoDto, userInfo);
                userInfoRepository.save(userInfo);
            }
            return userInfoDto;
        } catch (Exception e) {
            throw new AuthException("Not logged in yet");
        }
    }
}
