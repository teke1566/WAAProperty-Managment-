package com.waa.PropertyManagment.Repo;

import com.waa.PropertyManagment.Entity.Property;
import com.waa.PropertyManagment.Entity.SavedList;
import com.waa.PropertyManagment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedListRepository extends JpaRepository<SavedList, Long> {

List<SavedList> findByCustomerId(Long customerId);

Optional<SavedList> findByCustomerIdAndPropertiesId(Long customerId, Long propertyId);
}
