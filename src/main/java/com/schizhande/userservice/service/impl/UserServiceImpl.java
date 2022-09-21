package com.schizhande.userservice.service.impl;

import com.schizhande.userservice.dto.UserContactInformationDto;
import com.schizhande.userservice.feign.UserFeignClient;
import com.schizhande.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserContactInformationDto findUserContact(final long id, final String username) {
        return userFeignClient.getAllUsers()
                .stream()
                .filter(userDto -> userDto.getId() == id || userDto.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .map(UserContactInformationDto::mapToContact)
                .orElse(UserContactInformationDto.notFound());
    }
}
