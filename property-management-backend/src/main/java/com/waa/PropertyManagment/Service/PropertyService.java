package com.waa.PropertyManagment.Service;

import com.waa.PropertyManagment.Entity.Property;
import com.waa.PropertyManagment.Repo.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository){
        this.propertyRepository=propertyRepository;
    }

    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }


    public List<Property> filterPropertiesByPrice(Double minPrice, Double maxPrice) {
        return propertyRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Property> filterPropertiesByType(String propertyType) {
        return propertyRepository.findByPropertyType(propertyType);
    }

    public List<Property> filterPropertiesByNumberOfRooms(int numberOfRooms) {
        return propertyRepository.findByNumRooms(numberOfRooms);
    }

    public List<Property> filterPropertiesByHomeType(String homeType) {
        return propertyRepository.findByHomeType(homeType);
    }

    public List<Property> filterPropertiesByLocation(String location) {
        return propertyRepository.findByLocationContainingIgnoreCase(location);
    }

}
