package com.schizhande.userservice;

import com.schizhande.userservice.dto.UserContactInformationDto;
import com.schizhande.userservice.feign.UserFeignClient;
import com.schizhande.userservice.service.impl.UserServiceImpl;
import com.schizhande.userservice.utils.UserUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserFeignClient userFeignClient;

    @InjectMocks
    private UserServiceImpl userService;

    @ParameterizedTest
    @CsvSource({
            "1, Bret", //Match both id and username
            "1, Simba", // Match id only
            "10, Bret", // Match username only
    })
    void whenSearchUser_withValidOrValidUsername_thenTheCorrectContactInformationShouldBeReturned(long id, String username) {
        when(userFeignClient.getAllUsers()).thenReturn(UserUtils.generateRandomUsers());

        final UserContactInformationDto userContactInformation = userService.findUserContact(id, username);

        assertThat(userContactInformation.id()).isEqualTo(1);
        assertThat(userContactInformation.phone()).isEqualTo("1-770-736-8031 x56442");
        assertThat(userContactInformation.email()).isEqualTo("Sincere@april.biz");
    }

    @Test
    void whenSearchUser_withInvalidIdAndInValidUsername_thenTheCorrectContactInformationShouldNotBeReturned() {
        when(userFeignClient.getAllUsers()).thenReturn(UserUtils.generateRandomUsers());

        final UserContactInformationDto userContactInformation = userService.findUserContact(10, "Anonette");

        assertThat(userContactInformation.id()).isEqualTo(-1);
        assertThat(userContactInformation.phone()).isNull();
        assertThat(userContactInformation.email()).isNull();
    }
}
