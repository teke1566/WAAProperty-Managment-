package com.waa.PropertyManagment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String  propertyType;
    private  Integer numberOfRooms;
    private  String  city;
    private  Double  price;
    private  String status;


}
