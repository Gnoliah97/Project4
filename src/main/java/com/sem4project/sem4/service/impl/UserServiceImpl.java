package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.common.RoleEnum;
import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.entity.Role;
import com.sem4project.sem4.entity.User;
import com.sem4project.sem4.entity.UserDetailsImpl;
import com.sem4project.sem4.entity.UserInfo;
import com.sem4project.sem4.exception.AuthException;
import com.sem4project.sem4.mapper.RoleMapper;
import com.sem4project.sem4.mapper.UserInfoMapper;
import com.sem4project.sem4.mapper.UserMapper;
import com.sem4project.sem4.repository.RoleRepository;
import com.sem4project.sem4.repository.UserInfoRepository;
import com.sem4project.sem4.repository.UserRepository;
import com.sem4project.sem4.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
//@Transactional
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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
    public void register(RegisterRequest registerRequest) {
        if (!userRepository.existsByUsername(registerRequest.getUsername())) {
            String passwordEncoded = passwordEncoder.encode(registerRequest.getPassword());
            User userRegister = UserMapper.userFromRegisterRequest(registerRequest);
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
            UserDto userDto = UserMapper.userDtoFromUser(user);
            userDto.setRoles(user.getRoles().stream().map(RoleMapper::roleDtoFromRole).toList());
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
                user.setUserInfo(UserInfoMapper.mapUserInfoDtoToUserInfo(userInfoDto));
                userRepository.save(user);
            } else{
                UserInfoMapper.transferUserInfoDtoToUserInfo(userInfoDto, userInfo);
                userInfoRepository.save(userInfo);
            }
            return userInfoDto;
        } catch (Exception e) {
            throw new AuthException("Not logged in yet");
        }
    }
}
