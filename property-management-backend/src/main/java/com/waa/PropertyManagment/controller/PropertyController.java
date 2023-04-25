package com.waa.PropertyManagment.controller;

import com.waa.PropertyManagment.domain.Property;
import com.waa.PropertyManagment.dto.PropertyDto;
import com.waa.PropertyManagment.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Properties")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PropertyController {
   @Autowired
    private   PropertyService propertyService;
    @ResponseStatus(HttpStatus.OK)
   @GetMapping
   public List<PropertyDto> getAllProperties(){
       return  propertyService.getAllProperty();
   }

   @ResponseStatus(HttpStatus.CREATED)

   @PostMapping
   public void PostProperty(@RequestBody Property property){
         propertyService.post(property);
   }
@GetMapping("/{id}")
   public PropertyDto getPropertyById(@PathVariable Long id){
       return  propertyService.getPropertyById(id);
   }
   @PutMapping("/{id}")
   public  Property updateProperty(@RequestBody Property property, @PathVariable Long id){
       return  propertyService.updateProperty(property,id);
   }
    @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("/{id}")
public  void deleteProperty(@PathVariable Long id){
       propertyService.deleteProperty(id);
}
}
