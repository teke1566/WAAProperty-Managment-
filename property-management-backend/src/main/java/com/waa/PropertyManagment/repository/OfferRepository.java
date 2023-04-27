package com.waa.PropertyManagment.repository;

import com.waa.PropertyManagment.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
   // List<Offer> findByCustomer(User customer);
    //getOfferHistory

    //List<Offer> findByUserId(Long userId);
//    @Query("SELECT o FROM Offer o WHERE o.user.id = :userId")
//    List<Offer> findByUserId(Long userId);



    @Query("SELECT o FROM Offer o WHERE o.user.id = :customerId AND o.status = 'pending'")
    List<Offer> findActiveOffersByCustomerId(Long customerId);

    @Query("SELECT  o from  Offer o where o.user.id=:userId")
    List<Offer> findByUserId(Long userId);
}
