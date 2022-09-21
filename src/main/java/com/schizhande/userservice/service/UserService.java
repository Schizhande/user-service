package com.schizhande.userservice.service;

import com.schizhande.userservice.dto.UserContactInformationDto;

public interface UserService {
    UserContactInformationDto findUserContact(final long id, final String username);
}
