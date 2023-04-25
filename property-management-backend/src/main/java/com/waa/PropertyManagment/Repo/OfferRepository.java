package com.waa.PropertyManagment.Repo;

import com.waa.PropertyManagment.Entity.Offer;
import com.waa.PropertyManagment.Entity.SavedList;
import com.waa.PropertyManagment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
   // List<Offer> findByCustomer(User customer);
    //getOfferHistory

    //List<Offer> findByUserId(Long userId);
    @Query("SELECT o FROM Offer o WHERE o.user.id = :userId")
    List<Offer> findByUserId(Long userId);

    @Query("SELECT o FROM Offer o WHERE o.user.id = :customerId AND o.status = 'PENDING'")
    List<Offer> findActiveOffersByCustomerId(Long customerId);
}
