package com.schizhande.userservice.feign;

import com.schizhande.userservice.feign.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value="user-feign-client", url="${user.service.url}")
public interface UserFeignClient {

    @RequestMapping("/users")
    List<UserDto> getAllUsers();

}
