package com.schizhande.userservice.feign.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private long id;

    private String username;

    private String name;

    private String email;

    private AddressDto address;

    private String phone;

    private String website;

    private CompanyDto company;
}



