package com.waa.PropertyManagment.Service;

import com.waa.PropertyManagment.Entity.SavedList;
import com.waa.PropertyManagment.Repo.SavedListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedListService {
    private  final SavedListRepository savedListRepository;
    public SavedListService(SavedListRepository savedListRepository){
        this.savedListRepository=savedListRepository;
    }
//    public List<SavedList> findByUserId(Long userId) {
//        return savedListRepository.findByUserId(userId);
//    }
}
