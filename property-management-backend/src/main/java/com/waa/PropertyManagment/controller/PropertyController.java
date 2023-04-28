package com.waa.PropertyManagment.controller;

import com.waa.PropertyManagment.entity.Offer;
import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.entity.dto.PropertyDto;
import com.waa.PropertyManagment.service.PropertyService;
import com.waa.PropertyManagment.service.impl.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping("/owner")
    public void PostProperty(@RequestBody Property property) {
        propertyService.post(property);
    }

    @GetMapping("/{id}")
    public PropertyDto getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @PutMapping("/owner/{id}")
    public Property updateProperty(@RequestBody Property property, @PathVariable Long id) {
        return propertyService.updateProperty(property, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/owner/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }

    @PutMapping("/owner/{id}/pend")
    public Property UpdateToPending(@PathVariable Long id) {
        return propertyService.updateToPending(id);
    }

    @PutMapping("/owner/{id}/availabe")
    public void UpdateToAvailable(@PathVariable Long id) {
        propertyService.updateToAvailable(id);
    }

    @PutMapping("/owner/{id}/contigent")
    public void UpdateToCONTINGENT(@PathVariable Long id) {
        propertyService.updateToCONTINGENT(id);
    }

    @GetMapping("/city/{city}")
    public List<Property> getPropertyBycity(@PathVariable String city) {
        return propertyService.propertiesByAddress(city);
    }

    @GetMapping("/owner/{id}")
    List<Property> findPropertiesByUsers(@PathVariable Long id){
        return  propertyService.propertiesByUserId(id);
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

    @PutMapping("/owner/{id}/cancelContingency")
    public void cancelContingency(@PathVariable Long id) {
        propertyService.cancelContingency(id);
    }

    @GetMapping("/active-offer")//all active offer with status pending
    public ResponseEntity<List<Offer>> getAllActiveOffers() {
        List<Offer> activeOffers = offerService.findAllActiveOffers();
        return new ResponseEntity<>(activeOffers, HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}/active-offers")
    public List<Offer> getActiveOffersByOwnerId(@PathVariable Long ownerId){
        return  offerService.findActiveOffersByOwnerId(ownerId);
    }
    @GetMapping("/owner/{ownerId}/active-offers/properties")
    public List<Property> getActiveOfferProperties(@PathVariable Long ownerId){
        return offerService.findActiveOffersPropertiesForOwner(ownerId);

    }
    @GetMapping("/{ownerId}/active-offers/properties")
    public List<User> getActiveOfferCustomers(@PathVariable Long ownerId){
        return  offerService.findActiveOfferPropertiesCustomer(ownerId);
    }


    @GetMapping("/owner/{propertyId}")

 public List<Offer> getOffersByPropertyId(@PathVariable Long propertyId){
        return offerService.findOffersByPropertyId(propertyId);
    }

}