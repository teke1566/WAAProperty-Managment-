package com.waa.PropertyManagment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String propertyName;

    private String propertyType;
    private String description;
    private int numberOfRooms;

    private Double rentAmount;

    private Double securityDepositAmount;
    private int numberOfBathRooms;
    private String imageUrl;

    private double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;
//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    private User user;
}
