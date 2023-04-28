package com.waa.PropertyManagment.service.impl;

import com.waa.PropertyManagment.entity.Offer;
import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.Status;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.enums.OfferStatus;
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
        offer.setStatus(OfferStatus.PENDING);

        Offer savedOffer = offerRepository.save(offer);

        if (savedOffer != null) {
            property.setStatus(Status.PENDING);
            propertyRepository.save(property);
        }

        String ownerEmail = savedOffer.getProperty().getUsers_id().getEmail();
        //String ownerEmail1 ="getaunayaleneh@gmail.com";
        String subject = "New offer on your property";
        String message = "You have received a new offer of " + amount + " on your property at " +
                savedOffer.getProperty().getAddress().getCity();
        emailService.sendEmail(ownerEmail, subject, message);

        return savedOffer;
    }
}


