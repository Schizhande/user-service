package com.schizhande.userservice.api;

import com.schizhande.userservice.dto.UserContactInformationDto;
import com.schizhande.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("getusercontacts")
    public UserContactInformationDto getUserContact(@RequestParam final long id, @RequestParam final String username) {
        return userService.findUserContact(id, username);
    }
}
