package com.waa.PropertyManagment.controller;


import com.itextpdf.text.DocumentException;
import com.waa.PropertyManagment.entity.*;
import com.waa.PropertyManagment.service.PropertyService;
import com.waa.PropertyManagment.service.impl.CustomerService;
import com.waa.PropertyManagment.service.impl.FavoriteListService;
import com.waa.PropertyManagment.service.impl.MessageService;
import com.waa.PropertyManagment.service.impl.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Autowired
    private final CustomerService customerService;
@Autowired
    private final MessageService messageService;
@Autowired
    private final FavoriteListService favoriteListService;
@Autowired
    private final OfferService offerService;
@Autowired
    private  final PropertyService propertyService;

    public CustomerController(CustomerService customerService,
                              MessageService messageService,
                              FavoriteListService favoriteListService,
                              OfferService offerService,
                              PropertyService propertyService){
        this.customerService=customerService;
        this.messageService=messageService;
        this.favoriteListService = favoriteListService;
        this.offerService=offerService;
        this.propertyService=propertyService;
    }

    //http://localhost:8080/api/v1/customer/


    //http://localhost:8080/api/v1/customer/offer-history/1
    @GetMapping("/offer-history/{customerId}")
    public List<Offer> getOfferHistoryByCustomerId(@PathVariable Long customerId) {
        return offerService.findByCustomerId(customerId);
    }

    //http://localhost:8080/api/v1/customer/5/offer-history

    @GetMapping("/{customerId}/active-offers")
    public List<Offer> getActiveOffersByCustomerId(@PathVariable Long customerId){
        return  offerService.findActiveOffersByCustomerId(customerId);
    }

    //http://localhost:7070/api/v1/customer/offers/5/cancel
    @PostMapping("/offers/{offerId}/cancel")
    public ResponseEntity<String> cancelOffer(@PathVariable Long offerId){
        if(offerService.canCancelOffer(offerId)){
            offerService.cancelOffer(offerId);
            return ResponseEntity.ok("Offer canceled successfully");
        }
        else{
            return new ResponseEntity<>("Cannot cancel offer after contingency.", HttpStatus.BAD_REQUEST);
        }
    }
    //http://localhost:8080/api/v1/customer/offers/2/receipt
    @GetMapping("/offers/{offerId}/receipt")

    public ResponseEntity<byte[]> generateReceipt(@PathVariable Long offerId){
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



    /*To make the placeOffer program accept the amount
    parameter as a URL path variable instead of a request parameter
    , you can modify the @PostMapping annotation as follows
    * */


    //http://localhost:8080/api/v1/customer/place-offer?propertyId=1&userId=5&amount=100000
    @PostMapping("/place-offer")
    public ResponseEntity<Offer> placeOffer(
            @RequestParam Long propertyId,
            @RequestParam Long userId,
            @RequestParam Double amount) {
        Offer offer = customerService.placeOffer(propertyId, userId, amount);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }

      /*
    * {
    "id":8,
    "sender":{
        "id":4
    },
    "recipient":{
      "id":5
    },
    "content":"message content",
    "subject":"my_subject"
}
    * */

    @PostMapping("/send-messages")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message savedMessage = messageService.saveMessage(message);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }
    /*public ResponseEntity<Object> sendMessage(@PathVariable Long customerId,
                                              @RequestBody Message messageRequest) {
        messageService.sendMessage(customerId,messageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/


    /*
    * //ADD to savedlist
    * @PutMapping("/{savedListId}/properties/{propertyId}")
    public ResponseEntity<SavedList> addPropertyToSavedList(@PathVariable Long savedListId, @PathVariable Long propertyId) {
        SavedList savedList = savedListService.addPropertyToSavedList(savedListId, propertyId);
        if (savedList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(savedList, HttpStatus.OK);
    }
    * */


    //http://localhost:8080/api/v1/customer/add-favorites/2?propertyId=1&name="My Fav"

    @PostMapping("/add-favorites/{customerId}")
    public ResponseEntity<Object> addSavedProperty(@PathVariable Long customerId,
                                                   @RequestParam Long propertyId,
                                                   @RequestParam String name){
        favoriteListService.addSavedProperty(customerId,propertyId,name);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("get-favorites/{customerId}")
    public List<FavoriteList> getSavedProperties(@PathVariable Long customerId){
        return favoriteListService.getSavedProperties(customerId);
    }

    @DeleteMapping("/saved-properties/{savedPropertyId}")
    public ResponseEntity<Object> removeSavedProperty(@PathVariable Long savedPropertyId){
        favoriteListService.removeSavedProperty(savedPropertyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping("/properties/filter")
//    public List<Property> filterProperties(
//            @RequestParam(required = false) Double minPrice,
//            @RequestParam(required = false) Double maxPrice,
//            @RequestParam(required = false) String propertyType,
//            @RequestParam(required = false) Integer numberOfRooms,
//            @RequestParam(required = false) String homeType,
//            @RequestParam(required = false) String location) {
//        if (minPrice != null && maxPrice != null) {
//            return propertyService.filterPropertiesByPrice(minPrice, maxPrice);
//        } else if (propertyType != null) {
//            return propertyService.filterPropertiesByType(propertyType);
//        } else if (numberOfRooms != null) {
//            return propertyService.filterPropertiesByNumberOfRooms(numberOfRooms);
//        } else if (homeType != null) {
//            return propertyService.filterPropertiesByHomeType(homeType);
//        } else if (location != null) {
//            return propertyService.filterPropertiesByLocation(location);
//        } else {
//            return propertyService.getAllProperties();
//        }
//    }

}
