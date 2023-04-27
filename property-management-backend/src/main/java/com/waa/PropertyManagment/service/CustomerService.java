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

    public CustomerService(OfferRepository offerRepository, SavedListRepository savedListRepository, PropertyRepository propertyRepository, UserRepository userRepository, EmailService emailService) {
        this.offerRepository = offerRepository;
        this.savedListRepository = savedListRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

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
        offer.setStatus("pending");

        Offer savedOffer = offerRepository.save(offer);

        if (savedOffer != null) {
            property.setStatus("pending");
            propertyRepository.save(property);
        }

        return savedOffer;
    }
}


