package com.schizhande.userservice;

import com.schizhande.userservice.api.UserRestController;
import com.schizhande.userservice.dto.UserContactInformationDto;
import com.schizhande.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenSearchUser_withValidOrValidUsername_thenTheCorrectContactInformationShouldBeReturned() throws Exception {
        UserContactInformationDto userContactInformation = new UserContactInformationDto(1L,
                "Sincere@april.biz", "1-770-736-8031 x56442");

        when(userService.findUserContact(anyLong(), anyString())).thenReturn(userContactInformation);

        mockMvc.perform(get("/getusercontacts")
                        .param("id", String.valueOf(1L))
                        .param("username", "Bret"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.phone").value("1-770-736-8031 x56442"))
                .andExpect(jsonPath("$.email").value("Sincere@april.biz"));
    }

    @Test
    void whenSearchUser_withInvalidIdAndInValidUsername_thenTheCorrectContactInformationShouldNotBeReturned() throws Exception {

        UserContactInformationDto userContactInformation = new UserContactInformationDto(-1L,
                null, null);

        when(userService.findUserContact(anyLong(), anyString())).thenReturn(userContactInformation);

        mockMvc.perform(get("/getusercontacts")
                        .param("id", String.valueOf(10L))
                        .param("username", "Breto"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.phone").isEmpty())
                .andExpect(jsonPath("$.email").isEmpty());

    }
}
