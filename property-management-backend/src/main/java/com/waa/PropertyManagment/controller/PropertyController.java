package com.waa.PropertyManagment.controller;

import com.waa.PropertyManagment.entity.Offer;
import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.dto.PropertyDto;
import com.waa.PropertyManagment.enums.Roles;
import com.waa.PropertyManagment.service.PropertyService;
import com.waa.PropertyManagment.service.impl.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private OfferService offerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PropertyDto> getAllProperties() {
        return propertyService.getAllProperty();
    }


    //localhost:8080/api/v1/properties/owner/102
    @PostMapping("/owner/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void postProperty(@RequestBody @Valid Property property,@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority(Roles.OWNER.name()))) {
            throw new AccessDeniedException("You are not authorized to access this resource.");
        }
        propertyService.post(property,id);
    }

    //localhost:8080/api/v1/properties/1
    @GetMapping("/{id}")
    public PropertyDto getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }


//localhost:8080/api/v1/properties/owner/102
    @PutMapping("/owner/{id}")
    public Property updateProperty(@RequestBody Property property, @PathVariable Long id) {
        return propertyService.updateProperty(property, id);
    }

    //localhost:8080/api/v1/properties/owner/102
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/owner/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }

    //localhost:8080/api/v1/properties/owner/102/pend
    @PutMapping("/owner/{id}/pend")
    public Property UpdateToPending(@PathVariable Long id) {
        return propertyService.updateToPending(id);
    }


    //localhost:8080/api/v1/properties/owner/102/availabe
    @PutMapping("/owner/{id}/availabe")
    public void UpdateToAvailable(@PathVariable Long id) {
        propertyService.updateToAvailable(id);
    }

    // //localhost:8080/api/v1/properties/owner/102/contigent
    @PutMapping("/owner/{id}/contigent")
    public void UpdateToCONTINGENT(@PathVariable Long id) {
        propertyService.updateToCONTINGENT(id);
    }

    // //localhost:8080/api/v1/properties/city/miami
    @GetMapping("/city/{city}")
    public List<Property> getPropertyBycity(@PathVariable String city) {
        return propertyService.propertiesByAddress(city);
    }
// //localhost:8080/api/v1/properties/owner/102
    @GetMapping("/owner/{id}")
    List<Property> findPropertiesByUsers(@PathVariable Long id){
        return  propertyService.propertiesByUserId(id);
    }

// //localhost:8080/api/v1/properties/criteria
    @GetMapping("/criteria")
    public List<Property> getPropertyByCriteria(@RequestParam(value = "propertyType", required = false) String propertyType,
                                                @RequestParam(value = "city", required = false) String city,
                                                @RequestParam(value = "status", required = false) String status,
                                                @RequestParam(value = "price", required = false) Double price,
                                                @RequestParam(value = "numberOfRooms", required = false)
                                                Integer numberOfRooms) {
        // Check if the user has the required authorization
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority(Roles.OWNER.name()))) {
//            throw new AccessDeniedException("You are not authorized to access this resource.");
//        }

        return propertyService.findByAllCriteria(propertyType, city, status, price, numberOfRooms);
    }

    // //localhost:8080/api/v1/properties/owner/102/cancelContingency
    @PutMapping("/owner/{id}/cancelContingency")
    public void cancelContingency(@PathVariable Long id) {
        propertyService.cancelContingency(id);
    }


    //localhost:8080/api/v1/properties/active-offer
    @GetMapping("/active-offer")
    public ResponseEntity<List<Offer>> getAllActiveOffers() {
        List<Offer> activeOffers = offerService.findAllActiveOffers();
        return new ResponseEntity<>(activeOffers, HttpStatus.OK);
    }

    // Error handling for AccessDeniedException (403 Forbidden errors)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException(AccessDeniedException ex) {
        return ex.getMessage();
    }
}