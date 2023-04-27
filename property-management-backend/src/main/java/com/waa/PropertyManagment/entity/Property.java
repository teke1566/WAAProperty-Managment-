package com.waa.PropertyManagment.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String propertyType;
    private Integer numRooms;
    private String homeType;
    private String location;
    private Double price;
    private String status;


    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private User users_id;

    @ManyToOne
    @JoinColumn(name = "saved_list_id")
    @JsonIgnore
    private SavedList savedList;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Offer> offers;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private  List<Application> applications;
}
