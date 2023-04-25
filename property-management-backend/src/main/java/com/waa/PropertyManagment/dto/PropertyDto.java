package com.waa.PropertyManagment.dto;

import lombok.Data;

@Data
public class PropertyDto {

    private Long id;

    private String propertyName;

    private String propertyType;


    private int numberOfRooms;
    private int numberOfBathRooms;


    private Double rentAmount;

    private Double securityDepositAmount;

    private AddressDto address;
    private String imageUrl;


}
