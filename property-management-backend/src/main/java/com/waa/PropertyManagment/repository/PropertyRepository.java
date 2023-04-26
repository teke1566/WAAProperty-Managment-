package com.waa.PropertyManagment.repository;


import com.waa.PropertyManagment.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {

    @Query("select p from Property  p where p.address.city=?1")
    public List<Property>propertiesbyaddress(String city);

}