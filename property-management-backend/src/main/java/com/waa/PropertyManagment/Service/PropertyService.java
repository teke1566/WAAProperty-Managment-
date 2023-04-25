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
}
