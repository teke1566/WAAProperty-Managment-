package com.waa.PropertyManagment.Service;

import com.waa.PropertyManagment.Entity.Offer;
import com.waa.PropertyManagment.Entity.Property;
import com.waa.PropertyManagment.Entity.SavedList;
import com.waa.PropertyManagment.Entity.User;
import com.waa.PropertyManagment.Repo.OfferRepository;
import com.waa.PropertyManagment.Repo.PropertyRepository;
import com.waa.PropertyManagment.Repo.SavedListRepository;
import com.waa.PropertyManagment.Repo.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    private final OfferRepository offerRepository;

    private final SavedListRepository savedListRepository;

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public CustomerService( OfferRepository offerRepository, SavedListRepository savedListRepository, PropertyRepository propertyRepository, UserRepository userRepository){
        this.offerRepository=offerRepository;
        this.savedListRepository=savedListRepository;
        this.propertyRepository=propertyRepository;
        this.userRepository=userRepository;
    }

 public List<User> getAllCustomers(){
       return userRepository.findAllCustomers();
 }

//    public List<Offer> getOfferHistory(User customer){
//        return offerRepository.findByCustomer(customer);
//    }

  //  public List<Offer> getOfferHistory(Long userId){
   //     return offerRepository.findByUserId(userId);
    //}
    public void placeOffer(Long propertyId, User customer){
        Property property=propertyRepository.findById(propertyId).orElseThrow();

        if (property.getStatus().equals("available")){
            Offer offer= new Offer();
           // offer.setUserId(customer);
            offer.setProperty(property);
            offer.setStatus("pending");
            offerRepository.save(offer);

            property.setStatus("pending");
            propertyRepository.save(property);

        }
        else {
            throw  new IllegalStateException("Cannot place an offer on a non-available property");
        }
    }
   // public List<SavedList> getSavedLists(User customer){
       // return  savedListRepository.findByCustomer(customer);
   // }
    public void createSavedList(Long propertyId,String name,User customer){
        // the SavedList entity has a many-to-many relationship with the Property entity. A SavedList belongs to a User (customer) and contains a list of properties.
        Property property= propertyRepository.findById(propertyId).orElseThrow();
        SavedList savedList=new SavedList();
        savedList.setCustomer(customer);
        savedList.setName(name);
        savedList.setProperties((List<Property>) property);
        savedListRepository.save(savedList);
    }

//    public void removePropertyFromList(Long propertyId, User customer) {
//        Property property = propertyRepository.findById(propertyId);
//
//        SavedList savedProperty = savedListRepository.findByCustomerAndProperty(customer, property);
//        if (savedProperty != null) {
//            savedListRepository.delete(savedProperty.getId());
//        }
//        else {
//            System.out.println("Error..property couldn't locate");
//        }
//
//}
    public void removePropertyFromList(Long propertyId){
        Optional<Property> savedProperty= propertyRepository.findById(propertyId);
        if (savedProperty.isPresent()) {
            savedListRepository.deleteById(propertyId);
        }
        else {
            throw  new IllegalStateException("Error..property couldn't locate");
        }
    }
}
