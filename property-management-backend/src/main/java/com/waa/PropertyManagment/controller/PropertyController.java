package com.waa.PropertyManagment.controller;

import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.service.PropertyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private  final PropertyService propertyService;

    public PropertyController( PropertyService propertyService){
        this.propertyService=propertyService;
    }

    @GetMapping("/")
    public List<Property> getAllProperties(){
        return propertyService.getAllProperties();
    }
}
