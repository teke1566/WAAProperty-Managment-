package com.waa.PropertyManagment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property  {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String propertyName;

    private String propertyType;
    private String description;
    private int numberOfRooms;
// we use @JsonBackReference to solve the null exeption when returing on postman
    private Double rentAmount;

    private Double securityDepositAmount;
    private Integer numberOfBathRooms;
    private String imageUrl;

    private double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    @JsonBackReference
    private Address address;
    //staffes i added below

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonBackReference
    private User users_id;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "favotite_list_id")
    @JsonBackReference
    private FavoriteList favoriteList;

}
