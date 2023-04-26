package com.waa.PropertyManagment.Repo;

import com.waa.PropertyManagment.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Property> findByPropertyType(String propertyType);
    List<Property> findByNumRooms(int numberOfRooms);
    List<Property> findByHomeType(String homeType);

    List<Property> findByLocationContainingIgnoreCase(String location);
}
