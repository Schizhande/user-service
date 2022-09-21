package com.schizhande.userservice.dto;

import com.schizhande.userservice.feign.dto.UserDto;

public record UserContactInformationDto(long id, String email, String phone) {

    public static UserContactInformationDto mapToContact(final UserDto userDto) {
        return new UserContactInformationDto(userDto.getId(), userDto.getEmail(), userDto.getPhone());
    }

    public static UserContactInformationDto notFound() {
        return new UserContactInformationDto(-1, null, null);
    }
}
