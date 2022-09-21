
package com.schizhande.userservice;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.schizhande.userservice.config.WireMockConfig;
import com.schizhande.userservice.feign.UserFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static com.schizhande.userservice.config.UserMocks.setupMockUsersResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
class UsersClientIntegrationTest {

    @Autowired
    private WireMockServer mockUsersService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws IOException {
        setupMockUsersResponse(mockUsersService);
    }

    @Test
    void whenGetUsers_thenUsersShouldBeReturned() {
        assertFalse(userFeignClient.getAllUsers().isEmpty());
    }

    @Test
    void whenSearchUser_withValidOrValidUsername_thenTheCorrectContactInformationShouldBeReturned() throws Exception {
        String res = """
                {"id":1,"email":"Sincere@april.biz","phone":"1-770-736-8031 x56442"}""";
        assertEquals(mockMvc.perform(get("/getusercontacts")
                        .param("id", String.valueOf(1L))
                        .param("username", "Breto"))
                .andReturn().getResponse().getContentAsString(), res);

    }

    @Test
    void whenSearchUser_withInvalidIdAndInValidUsername_thenTheCorrectContactInformationShouldNotBeReturned() throws Exception {
        String res = """
                {"id":-1,"email":null,"phone":null}""";
        assertEquals(mockMvc.perform(get("/getusercontacts")
                        .param("id", String.valueOf(10L))
                        .param("username", "Breto"))
                .andReturn().getResponse().getContentAsString(), res);

    }

}
