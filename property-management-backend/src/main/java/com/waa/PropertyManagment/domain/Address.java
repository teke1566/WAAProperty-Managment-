package com.waa.PropertyManagment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String street;
    private  String  city;
    private  String state;
    private  String zipCode;
    @OneToOne(mappedBy = "address")
    private  Property property;
}
