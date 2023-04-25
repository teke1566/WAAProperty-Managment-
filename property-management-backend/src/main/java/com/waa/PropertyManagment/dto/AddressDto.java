package com.waa.PropertyManagment.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;

    private String street;

    private String city;

    private String zipcode;

    private String state;

}
