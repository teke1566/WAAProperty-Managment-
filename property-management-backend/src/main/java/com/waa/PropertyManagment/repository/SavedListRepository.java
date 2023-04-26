package com.waa.PropertyManagment.repository;

import com.waa.PropertyManagment.entity.SavedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedListRepository extends JpaRepository<SavedList, Long> {

List<SavedList> findByCustomerId(Long customerId);

Optional<SavedList> findByCustomerIdAndPropertiesId(Long customerId, Long propertyId);
}
