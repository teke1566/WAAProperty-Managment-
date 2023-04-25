package com.waa.PropertyManagment.service;


import com.waa.PropertyManagment.domain.Property;
import com.waa.PropertyManagment.dto.PropertyDto;

import java.util.List;


public interface PropertyService {
    void post(Property property);

    List<PropertyDto> getAllProperty();

    PropertyDto getPropertyById(Long id);


    void deleteProperty(Long id);

    Property updateProperty(Property property, Long userId);
}
