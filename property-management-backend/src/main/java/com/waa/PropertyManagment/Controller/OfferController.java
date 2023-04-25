package com.waa.PropertyManagment.Controller;

import com.waa.PropertyManagment.Service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner/offer")
public class OfferController {
    @Autowired
    private OfferService offerService;

    // Offer management operations for owners
    // e.g., Reject offer, Accept offer, Cancel contingency
}
