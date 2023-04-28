package com.waa.PropertyManagment.service.impl;

import com.waa.PropertyManagment.entity.FavoriteList;
import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.repository.FavoriteListRepository;
import com.waa.PropertyManagment.repository.PropertyRepository;

import com.waa.PropertyManagment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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


    /*
    * public Property addPropertyToFavoriteList(Long customerId, Long propertyId, String name) {
        Optional<User> userOptional = userRepository.findById(customerId);
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);

        if (userOptional.isEmpty() || propertyOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        Property property = propertyOptional.get();

        Optional<FavoriteList> favoriteListOptional = favoriteListRepository.findByCustomerAndPropertiesContaining(user, property);

        FavoriteList favoriteList;
        if (favoriteListOptional.isPresent()) {
            favoriteList = favoriteListOptional.get();
        } else {
            favoriteList = new FavoriteList();
            favoriteList.setCustomer(user);
            favoriteList.setName(name);
            favoriteList.setProperties(new ArrayList<>());
            favoriteList = favoriteListRepository.save(favoriteList);
        }

        property.setFavoriteList(favoriteList);
        Property updatedProperty = propertyRepository.save(property);
        return updatedProperty;
    }
    * */






    public void addToFavoriteList(Long customerId, Long propertyId,String name){
        Optional<FavoriteList> savedListOptional = savedListRepository.findByCustomerIdAndPropertiesId(customerId,propertyId);

        Property property = propertyRepository.findById(propertyId).get();
        User user= userRepository.findById(customerId).get();

        if(savedListOptional.isEmpty()){
            FavoriteList favoriteList =new FavoriteList();
            favoriteList.setName(name);
            favoriteList.setProperties(Arrays.asList(property));
            favoriteList.setCustomer(user);
            savedListRepository.save(favoriteList);
        }
        else {
            property.setFavoriteList(savedListOptional.get());
            propertyRepository.save(property);
        }
    }

    public List<FavoriteList> getFavoriteList(Long customerId){
        return savedListRepository.findByCustomerId(customerId);
    }
    public void removeFavoriteList(Long savedPropertyId){
        savedListRepository.deleteById(savedPropertyId);
    }

}
