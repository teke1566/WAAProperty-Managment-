package com.waa.PropertyManagment.repository;

import com.waa.PropertyManagment.entity.Offer;
import com.waa.PropertyManagment.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository  extends JpaRepository<Offer,Long> {

    List<Offer> findByUserId(Long userId);

    @Query("SELECT o FROM Offer o WHERE o.user.id = :customerId AND o.status = 'PENDING'")
    List<Offer> findActiveOffersByCustomerId(Long customerId);

    List<Offer> findByStatus(Status status);



}
