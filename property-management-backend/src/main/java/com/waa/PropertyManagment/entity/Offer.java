package com.waa.PropertyManagment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.waa.PropertyManagment.enums.OfferStatus;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //One property can have many offers associated with it
    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonBackReference
    private Property property;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    User user;
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
    private Double amount;

}
