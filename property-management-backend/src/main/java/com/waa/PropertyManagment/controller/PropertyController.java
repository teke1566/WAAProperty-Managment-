package com.waa.PropertyManagment.controller;

import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.dto.PropertyDto;
import com.waa.PropertyManagment.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Properties")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PropertyDto> getAllProperties() {
        return propertyService.getAllProperty();
    }

    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping
    public void PostProperty(@RequestBody Property property) {
        propertyService.post(property);
    }

    @GetMapping("/{id}")
    public PropertyDto getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @PutMapping("/{id}")
    public Property updateProperty(@RequestBody Property property, @PathVariable Long id) {
        return propertyService.updateProperty(property, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }

    @PutMapping("/{id}/pend")
    public Property UpdateToPending(@PathVariable Long id) {
        return propertyService.updateToPending(id);
    }

    @PutMapping("/{id}/availabe")
    public void UpdateToAvailable(@PathVariable Long id) {
        propertyService.updateToAvailable(id);
    }

    @PutMapping("/{id}/contigent")
    public void UpdateToCONTINGENT(@PathVariable Long id) {
        propertyService.updateToCONTINGENT(id);
    }

    @GetMapping("/city/{city}")
    public List<Property> getPropertyBycity(@PathVariable String city) {
        return propertyService.propertiesByAddress(city);
    }


    @GetMapping("/criteria")
    public List<Property> getPropertyByCriteria(@RequestParam(value = "propertyType", required = false) String propertyType,
                                                @RequestParam(value = "city", required = false) String city,
                                                @RequestParam(value = "status", required = false) String status,
                                                @RequestParam(value = "price", required = false) Double price,
                                                @RequestParam(value = "numberOfRooms", required = false)
                                                Integer numberOfRooms) {
        return propertyService.findByAllCriteria(propertyType, city, status, price, numberOfRooms);
    }

    @PutMapping("/{id}/cancelContingency")
    public void cancelContingency(@PathVariable Long id) {
        propertyService.cancelContingency(id);
    }
}
