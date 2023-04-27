package com.waa.PropertyManagment.service.impl;

import com.waa.PropertyManagment.entity.Offer;
import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.Status;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.repository.FavoriteListRepository;
import com.waa.PropertyManagment.repository.OfferRepository;
import com.waa.PropertyManagment.repository.PropertyRepository;
import com.waa.PropertyManagment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
@Autowired
    private OfferRepository offerRepository;
@Autowired
    private  FavoriteListRepository favoriteListRepository;
@Autowired
    private  PropertyRepository propertyRepository;
@Autowired
    private  UserRepository userRepository;
@Autowired
    private  EmailService emailService;



    public List<User> getAllCustomers() {
        return userRepository.findAllCustomers();
    }


 /*
 *Place offer, the property status will be changed to 'pending'
 *  if the offer gets accepted:
When a customer places an offer, update the
*  property status to 'pending' if the offer is accepted.
*  You can implement this logic in the CustomerService.
 * */

   /* public void placeOffer(Long customerId, Long propertyId,Long offerId, Double amount) {
        Optional<User> userCondition = userRepository.findById(customerId);
        Optional<Property> propertyCondition = propertyRepository.findById(propertyId);

        if (userCondition.isPresent() && propertyCondition.isPresent()) {
            User user = userCondition.get();
            Property property = propertyCondition.get();

            if (property.getStatus().equals("AVAILABLE")) {
                Offer savedOffer;
                Offer offer;
                Optional<Offer> offerCondition = offerRepository.findById(offerId);
                if (offerCondition.isPresent()) {
                    offer = offerCondition.get();
                    offer.setAmount(amount);
                    offer.setStatus(Status.PENDING);
                    savedOffer=offerRepository.save(offer);
                    property.setStatus(Status.PENDING);
                    propertyRepository.save(property);
                } else {
                    offer = new Offer();
                    offer.setUser(user);
                    offer.setProperty(property);
                    offer.setAmount(amount);
                    offer.setStatus(Status.PENDING);
                    savedOffer=offerRepository.save(offer);
                    property.setStatus(Status.PENDING);
                    propertyRepository.save(property);
                }
                //Email

                String ownerEmail = savedOffer.getProperty().getUsers_id().getEmail();
                String subject = "New offer on your property";
                String message = "You have received a new offer of " + amount + " on your property at " +
                        savedOffer.getProperty().getAddress();
                emailService.sendEmail(ownerEmail, subject, message);



                // Update the property status to 'pending' if the offer is accepted
                //for now it only check if the offered amount is greater than or equal to
                //the property price

                if (amount >= property.getPrice()) {
                    property.setStatus(Status.PENDING);
                    offer.setStatus(Status.PENDING);
                    propertyRepository.save(property);
                    offerRepository.save(offer);
                }
            }

        } else {
            System.out.println("Cannot place order on non available property");
            throw new IllegalStateException("Cannot place an offer on a non-available property");
        }

    }*/

    public Offer placeOffer(Long propertyId, Long userId, Double amount) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property == null) {
            return null;
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        Offer offer = new Offer();
        offer.setProperty(property);
        offer.setUser(user);
        offer.setAmount(amount);
        offer.setStatus(Status.PENDING);

        Offer savedOffer = offerRepository.save(offer);

        if (savedOffer != null) {
            property.setStatus(Status.PENDING);
            propertyRepository.save(property);
        }

        String ownerEmail = savedOffer.getProperty().getUsers_id().getEmail();
        String subject = "New offer on your property";
        String message = "You have received a new offer of " + amount + " on your property at " +
                savedOffer.getProperty().getAddress().getCity();
        emailService.sendEmail(ownerEmail, subject, message);

        return savedOffer;
    }
}


