package com.waa.PropertyManagment.Controller;

import com.itextpdf.text.DocumentException;
import com.waa.PropertyManagment.Entity.Offer;
import com.waa.PropertyManagment.Entity.Property;
import com.waa.PropertyManagment.Entity.SavedList;
import com.waa.PropertyManagment.Entity.User;
import com.waa.PropertyManagment.Service.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final MessageService messageService;
    private final SavedListService savedListService;

    private final OfferService offerService;
    private  final PropertyService propertyService;

    public CustomerController(CustomerService customerService,
                              MessageService messageService,
                              SavedListService savedListService,
                              OfferService offerService,
                              PropertyService propertyService){
        this.customerService=customerService;
        this.messageService=messageService;
        this.savedListService=savedListService;
        this.offerService=offerService;
        this.propertyService=propertyService;
    }

    @GetMapping("/")
    public List<User> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}/offer-history")
    public List<Offer> getOfferHistoryByCustomerId(@PathVariable Long customerId) {
        return offerService.findByCustomerId(customerId);
    }

    @GetMapping("/{customerId}/active-offers")
    public List<Offer> getActiveOffersByCustomerId(@PathVariable Long customerId){
        return  offerService.findActiveOffersByCustomerId(customerId);
    }

    //http://localhost:7070/api/v1/customer/6/offers/5/cancel
    @PostMapping("/{customerId}/offers/{offerId}/cancel")
    public ResponseEntity<String> cancelOffer(@PathVariable Long customerId, @PathVariable Long offerId){
        if(offerService.canCancelOffer(offerId)){
            offerService.cancelOffer(offerId);
            return ResponseEntity.ok("Offer canceled successfully");
        }
        else{
            return new ResponseEntity<>("Cannot cancel offer after contingency.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{customerId}/offers/{offerId}/receipt")

    public ResponseEntity<byte[]> generateReceipt(@PathVariable Long customerId, @PathVariable Long offerId){
        try {
            ByteArrayInputStream bis= offerService.generateReceipt(offerId);
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment","receipt.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(bis.readAllBytes(),headers,HttpStatus.OK);


        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{customerId}/properties/{propertyId}/offers")

    public ResponseEntity<Object> placeOffer(@PathVariable Long customerId,
                                             @PathVariable Long propertyId,
                                             @RequestParam Double amount){
        customerService.placeOffer(customerId,propertyId,amount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{customerId}/messages")
    public ResponseEntity<Object> sendMessage(@PathVariable Long customerId,
                                              @RequestParam Long recipientId,
                                              @RequestParam String subject,
                                              @RequestParam String content){
        messageService.sendMessage(customerId,recipientId,content,subject);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{customerId}/saved-properties")
    public ResponseEntity<Object> addSavedProperty(@PathVariable Long customerId,
                                                   @RequestParam Long propertyId,
                                                   @RequestParam String name){
        savedListService.addSavedProperty(customerId,propertyId,name);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{customerId}/saved-properties")
    public List<SavedList> getSavedProperties(@PathVariable Long customerId){
        return savedListService.getSavedProperties(customerId);
    }

    @DeleteMapping("/saved-properties/{savedPropertyId}")
    public ResponseEntity<Object> removeSavedProperty(@PathVariable Long savedPropertyId){
        savedListService.removeSavedProperty(savedPropertyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/properties/filter")
    public List<Property> filterProperties(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) Integer numberOfRooms,
            @RequestParam(required = false) String homeType,
            @RequestParam(required = false) String location) {
        if (minPrice != null && maxPrice != null) {
            return propertyService.filterPropertiesByPrice(minPrice, maxPrice);
        } else if (propertyType != null) {
            return propertyService.filterPropertiesByType(propertyType);
        } else if (numberOfRooms != null) {
            return propertyService.filterPropertiesByNumberOfRooms(numberOfRooms);
        } else if (homeType != null) {
            return propertyService.filterPropertiesByHomeType(homeType);
        } else if (location != null) {
            return propertyService.filterPropertiesByLocation(location);
        } else {
            return propertyService.getAllProperties();
        }
    }

}
