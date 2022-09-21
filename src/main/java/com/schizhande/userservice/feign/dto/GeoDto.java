package com.schizhande.userservice.feign.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GeoDto {

    private String lat;

    private String lng;
}
