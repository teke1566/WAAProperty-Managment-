package com.waa.PropertyManagment.service.impl;

import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.Status;
import com.waa.PropertyManagment.entity.dto.PropertyDto;
import com.waa.PropertyManagment.entity.dto.PropertySearchDao;
import com.waa.PropertyManagment.entity.dto.SearchCriteria;
import com.waa.PropertyManagment.repository.PropertyRepository;
import com.waa.PropertyManagment.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Propertyserviceimpl implements PropertyService {
    @Autowired
    PropertySearchDao propertySearchDao;

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
    public List<Property> propertiesByAddress(String city) {
        List<Property> properties = new ArrayList<>();
        for (Property p : propertyRepository.propertiesbyaddress(city)) {
            properties.add(p);
        }
        return properties;
    }

    @Override
    public void cancelContingency(Long id) {
        updateToAvailable(id);
    }

    @Override
    public Property updateToPending(Long id) {
        Property property = propertyRepository.findById(id).get();

        property.setStatus(Status.PENDING);
        propertyRepository.save(property);
        return property;
    }

    @Override
    public Property updateToAvailable(Long id) {
        Property property = propertyRepository.findById(id).get();
        property.setStatus(Status.AVAILABLE);
        propertyRepository.save(property);
        return property;
    }

    @Override
    public Property updateToCONTINGENT(Long id) {
        Property property = propertyRepository.findById(id).get();
        property.setStatus(Status.CONTINGENT);
        propertyRepository.save(property);
        return property;
    }

    @Override
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id).get();
        Status status = Status.PENDING;
        if (!property.getStatus().equals(status)) {
            propertyRepository.deleteById(id);
        }
        System.out.println("property is pending can not be deleted");
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

    @Override
    public List<Property> findByAllCriteria(String propertyType, String city, String status, Double price, Integer numberOfRooms) {
        var dtoSearchRequest=new SearchCriteria();
        dtoSearchRequest.setPropertyType(propertyType);
        dtoSearchRequest.setCity(city);
        dtoSearchRequest.setStatus(status);
        dtoSearchRequest.setPrice(price);
        dtoSearchRequest.setNumberOfRooms(numberOfRooms);
        return  propertySearchDao.findAllByCriteria(dtoSearchRequest);
    }


}
