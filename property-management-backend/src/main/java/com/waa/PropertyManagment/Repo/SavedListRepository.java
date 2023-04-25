package com.waa.PropertyManagment.Repo;

import com.waa.PropertyManagment.Entity.Property;
import com.waa.PropertyManagment.Entity.SavedList;
import com.waa.PropertyManagment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedListRepository extends JpaRepository<SavedList, Long> {

   // List<SavedList> findByUserId(Long userId);

    // SavedList findByCustomerAndProperty(User customer, Property property);

    //List<SavedList> findByCustomer(User customer);
    //used to specifically find and remove property from savedList.
}
