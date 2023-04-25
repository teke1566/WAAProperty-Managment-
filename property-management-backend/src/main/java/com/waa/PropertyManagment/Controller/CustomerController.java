package com.waa.PropertyManagment.Controller;

import com.waa.PropertyManagment.Entity.Offer;
import com.waa.PropertyManagment.Entity.Property;
import com.waa.PropertyManagment.Entity.SavedList;
import com.waa.PropertyManagment.Entity.User;
import com.waa.PropertyManagment.Service.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final MessageService messageService;
    private final SavedListService savedListService;

    private final OfferService offerService;

    public CustomerController(CustomerService customerService, MessageService messageService, SavedListService savedListService, OfferService offerService){
        this.customerService=customerService;
        this.messageService=messageService;
        this.savedListService=savedListService;
        this.offerService=offerService;
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

//    @GetMapping("/offers")
//    public String offerHistory(Model model, @AuthenticationPrincipal User customer) {
//        model.addAttribute("offers", customerService.getOfferHistory(customer));
//        return "customer/offers";
//    }
//    @GetMapping("/offers/{userId}")
//    public List<Offer> offerHistory(@PathVariable Long userId){
//        return customerService.getOfferHistory(userId);
//    }

//    @GetMapping("/saved-lists/{userId}")
//    public List<SavedList> getSavedListsByUserId(@PathVariable Long userId) {
//        return savedListService.findByUserId(userId);
//    }


    //still need to make change

//    @PostMapping("/place-offer/{propertyId}")
//    public String placeOffer(@PathVariable Long propertyId){
//        customerService.placeOffer(propertyId);
//        return "redirect:/api/customer/offers";
//    }
//    @PostMapping("/send-message/{ownerId}")
//    public String sendMessage(@PathVariable Long ownerId, @RequestParam("content") String content){
//        messageService.sendMessage(ownerId,content);
//        return "redirect:/customer/messages";
//    }

//    @GetMapping("/saved-lists")
//    public String getSavedLists(Model model, @AuthenticationPrincipal User customer) {
//        model.addAttribute("savedLists", customerService.getSavedLists(customer));
//        return "customer/saved-lists";
//    }

//    @PostMapping("/create-saved-property/{propertyId}")
//    public String createSavedList(@PathVariable Long propertyId, @RequestParam("name") String name){
//        customerService.createSavedList(propertyId,name,customer);
//        return "redirect:/customer/saved-lists";
//    }
//
//    @DeleteMapping("remove-saved-property/{propertyId}")
//
//    public String removeSavedproperty(@PathVariable Long propertyId){
//        customerService.removePropertyFromList(propertyId);
//        return "redirect:/customer/saved-lists";
//    }
}
