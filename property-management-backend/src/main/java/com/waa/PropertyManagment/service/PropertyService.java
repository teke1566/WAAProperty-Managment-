package com.waa.PropertyManagment.service;


import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.dto.PropertyDto;

import java.util.List;


public interface PropertyService {
    void post(Property property);

    List<PropertyDto> getAllProperty();

    PropertyDto getPropertyById(Long id);

     List<Property> propertiesByAddress(String city) ;

    void cancelContingency(Long id);

    Property updateToPending(Long id);

    Property updateToAvailable(Long id);

    Property updateToCONTINGENT(Long id);

    void deleteProperty(Long id);

    Property updateProperty(Property property, Long userId);
    public List<Property>findByAllCriteria(String propertyType,String city,String status,Double price, Integer numberOfRooms );
}
