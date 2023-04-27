package com.waa.PropertyManagment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


/*b.	Maintain current offers placed:
        Create an endpoint to retrieve and display the active
         offers for the current customer. You can filter offers based
          on their status (e.g., "active" or "inactive").*/

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
    private Status status;
    private Double amount;
}
