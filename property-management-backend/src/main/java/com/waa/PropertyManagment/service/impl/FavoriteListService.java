package com.waa.PropertyManagment.service.impl;

import com.waa.PropertyManagment.entity.FavoriteList;
import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.repository.FavoriteListRepository;
import com.waa.PropertyManagment.repository.PropertyRepository;

import com.waa.PropertyManagment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteListService {
    private  final FavoriteListRepository savedListRepository;
    private final PropertyRepository propertyRepository;
    private  final UserRepository userRepository;
    public FavoriteListService(FavoriteListRepository favoriteListRepository,
                               PropertyRepository propertyRepository,
                               UserRepository userRepository){
        this.savedListRepository= favoriteListRepository;
        this.propertyRepository=propertyRepository;
        this.userRepository=userRepository;
    }

    public void addSavedProperty(Long customerId, Long propertyId,String name){
        Optional<FavoriteList> savedListOptional = savedListRepository.findByCustomerIdAndPropertiesId(customerId,propertyId);
        Property property = propertyRepository.findById(propertyId).get();
        User user= userRepository.findById(customerId).get();

        if(savedListOptional.isEmpty()){
            FavoriteList favoriteList =new FavoriteList();
            favoriteList.setName(name);
            favoriteList.setProperties((List<Property>) property);
            favoriteList.setCustomer(user);
            savedListRepository.save(favoriteList);
        }
    }


/*

    public SavedList getSavedList(Long id) {
        return savedListRepository.findById(id).orElse(null);
    }
    public SavedList addPropertyToSavedList(Long savedListId, Long propertyId) {
        SavedList savedList = getSavedList(savedListId);
        if (savedList == null) {
            return null;
        }

        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property == null) {
            return null;
        }

        property.setSavedList(savedList);
        propertyRepository.save(property);
        return savedList;
    }

*/

    public List<FavoriteList> getSavedProperties(Long customerId){
        return savedListRepository.findByCustomerId(customerId);
    }
    public void removeSavedProperty(Long savedPropertyId){
        savedListRepository.deleteById(savedPropertyId);
    }

}
