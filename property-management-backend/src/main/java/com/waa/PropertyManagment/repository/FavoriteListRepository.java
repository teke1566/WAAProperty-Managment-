package com.waa.PropertyManagment.repository;

import com.waa.PropertyManagment.entity.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList,Long> {
    List<FavoriteList> findByCustomerId(Long customerId);

    Optional<FavoriteList> findByCustomerIdAndPropertiesId(Long customerId, Long propertyId);

}
