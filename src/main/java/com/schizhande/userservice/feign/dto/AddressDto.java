package com.schizhande.userservice.feign.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDto {

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    private GeoDto geo;
}

