package com.schizhande.userservice.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class UserMocks {

    public static void setupMockUsersResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/users"))
          .willReturn(
            WireMock.aResponse()
              .withStatus(HttpStatus.OK.value())
              .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
              .withBody(
                copyToString(
                  UserMocks.class.getClassLoader().getResourceAsStream("payload/get-users-response.json"),
                  defaultCharset()))));
    }

}
