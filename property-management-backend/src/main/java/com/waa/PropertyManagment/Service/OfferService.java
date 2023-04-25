package com.waa.PropertyManagment.Service;

import com.waa.PropertyManagment.Entity.Offer;
import com.waa.PropertyManagment.Repo.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository){
        this.offerRepository=offerRepository;
    }
    public List<Offer> findByCustomerId(Long customerId) {
        return offerRepository.findByUserId(customerId);
    }
    public List<Offer> findActiveOffersByCustomerId(Long customerId){
       return  offerRepository.findActiveOffersByCustomerId(customerId);
    }
}
