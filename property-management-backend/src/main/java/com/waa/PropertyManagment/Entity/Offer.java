package com.waa.PropertyManagment.Entity;

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

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;


    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    User user;
    private String status;
    private Double amount;
}