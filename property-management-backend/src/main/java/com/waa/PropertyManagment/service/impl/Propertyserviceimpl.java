package com.waa.PropertyManagment.service.impl;

import com.waa.PropertyManagment.domain.Property;
import com.waa.PropertyManagment.domain.Status;
import com.waa.PropertyManagment.dto.PropertyDto;
import com.waa.PropertyManagment.repository.PropertyRepository;
import com.waa.PropertyManagment.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Propertyserviceimpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ModelMapper modelmapper;

    @Override
    public void post(Property property) {
        try {
            Status status = Status.AVAILABLE;

            //var status = StatusRepo.findByid(1).get();
//        property.setStatus(status);


            property.setStatus(status);
            propertyRepository.save(property);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<PropertyDto> getAllProperty() {
        List<PropertyDto> propertyDtos = new ArrayList<>();
        for (Property property : propertyRepository.findAll()) {
            var dto = modelmapper.map(property, PropertyDto.class);
            propertyDtos.add(dto);
        }
        return propertyDtos;
    }

    @Override
    public PropertyDto getPropertyById(Long id) {

        Property property = propertyRepository.findById(id).get();
        return modelmapper.map(property, PropertyDto.class);
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public Property updateProperty(Property property, Long userId) {
        Property toUpate = propertyRepository.findById(userId).orElse(null);
        if (toUpate != null) {
            toUpate.setPropertyType(property.getPropertyType());
            toUpate.setPropertyName(property.getPropertyName());
            toUpate.setDescription(property.getDescription());
            toUpate.setNumberOfBathRooms(property.getNumberOfBathRooms());
            toUpate.setAddress(property.getAddress());
            toUpate.setPrice(property.getPrice());
            toUpate.setNumberOfRooms(property.getNumberOfRooms());
            toUpate.setPostedDate(property.getPostedDate());
            toUpate.setRentAmount(property.getRentAmount());
            toUpate.setPostedDate(property.getPostedDate());

            //toUpate.setUser(property.getUser());
            toUpate.setSecurityDepositAmount(property.getSecurityDepositAmount());
            toUpate.setImageUrl(property.getImageUrl());
            propertyRepository.save(toUpate);
        }
        return toUpate;
    }
}
