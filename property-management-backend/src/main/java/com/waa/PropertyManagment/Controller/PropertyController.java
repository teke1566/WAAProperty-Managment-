package com.waa.PropertyManagment.Controller;

import com.waa.PropertyManagment.Entity.Property;
import com.waa.PropertyManagment.Service.PropertyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
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
