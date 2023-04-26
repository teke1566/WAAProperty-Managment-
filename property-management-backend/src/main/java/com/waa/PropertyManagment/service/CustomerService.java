package com.waa.PropertyManagment.service;

import com.waa.PropertyManagment.entity.Offer;
import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.repository.OfferRepository;
import com.waa.PropertyManagment.repository.PropertyRepository;
import com.waa.PropertyManagment.repository.SavedListRepository;
import com.waa.PropertyManagment.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    private final OfferRepository offerRepository;

    private final SavedListRepository savedListRepository;

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public CustomerService(OfferRepository offerRepository, SavedListRepository savedListRepository, PropertyRepository propertyRepository, UserRepository userRepository, EmailService emailService){
        this.offerRepository=offerRepository;
        this.savedListRepository=savedListRepository;
        this.propertyRepository=propertyRepository;
        this.userRepository=userRepository;
        this.emailService = emailService;
    }

 public List<User> getAllCustomers(){
       return userRepository.findAllCustomers();
 }


 /*
 *Place offer, the property status will be changed to 'pending'
 *  if the offer gets accepted:
When a customer places an offer, update the
*  property status to 'pending' if the offer is accepted.
*  You can implement this logic in the CustomerService.
 * */

    public void placeOffer(Long customerId, Long propertyId, Double amount){
        Property property = propertyRepository.findById(propertyId).get();
        User user = userRepository.findById(customerId).get();
        Optional<User> userCondition=userRepository.findById(customerId);
        if (property.getStatus().equals("available")&&(userCondition.isPresent())) {
            // Create a new offer for the property and customer
            Offer offer = new Offer();
            offer.setUser(user);
            offer.setProperty(property);
            offer.setAmount(amount);
            offer.setStatus("Pending");
            Offer savedOffer=offerRepository.save(offer);

            //Email

            String ownerEmail = savedOffer.getProperty().getUsers_id().getEmail();
            String subject = "New offer on your property";
            String message = "You have received a new offer of " + amount + " on your property at " +
                    savedOffer.getProperty().getLocation();
            emailService.sendEmail(ownerEmail, subject, message);




            // Update the property status to 'pending' if the offer is accepted
            //for now it only check if the offered amount is greater than or equal to
            //the property price

            if (amount >= property.getPrice()) {
                property.setStatus("pending");
                offer.setStatus("Accepted");
                propertyRepository.save(property);
                offerRepository.save(offer);
            }
        }
        else {
            throw  new IllegalStateException("Cannot place an offer on a non-available property");
        }

    }

}
