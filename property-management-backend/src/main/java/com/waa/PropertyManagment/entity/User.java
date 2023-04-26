package com.waa.PropertyManagment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

//    @OneToMany(mappedBy = "owner")
//    private List<Property> properties;
//
//    @OneToMany(mappedBy = "customer")
//    private List<FavouriteProperty> favourites;



}
