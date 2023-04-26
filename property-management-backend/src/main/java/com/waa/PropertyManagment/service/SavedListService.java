package com.waa.PropertyManagment.service;

import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.SavedList;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.repository.PropertyRepository;
import com.waa.PropertyManagment.repository.SavedListRepository;
import com.waa.PropertyManagment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavedListService {
    private  final SavedListRepository savedListRepository;
    private final PropertyRepository propertyRepository;
    private  final UserRepository userRepository;
    public SavedListService(SavedListRepository savedListRepository,
                            PropertyRepository propertyRepository,
                            UserRepository userRepository){
        this.savedListRepository=savedListRepository;
        this.propertyRepository=propertyRepository;
        this.userRepository=userRepository;
    }

    public void addSavedProperty(Long customerId, Long propertyId,String name){
        Optional<SavedList> savedListOptional = savedListRepository.findByCustomerIdAndPropertiesId(customerId,propertyId);
        Property property = propertyRepository.findById(propertyId).get();
        User user= userRepository.findById(customerId).get();

        if(savedListOptional.isEmpty()){
            SavedList savedList=new SavedList();
            savedList.setName(name);
            savedList.setProperties(property);
            savedList.setCustomer(user);
            savedListRepository.save(savedList);
        }
    }
    public List<SavedList> getSavedProperties(Long customerId){
        return savedListRepository.findByCustomerId(customerId);
    }
    public void removeSavedProperty(Long savedPropertyId){
        savedListRepository.deleteById(savedPropertyId);
    }
}
