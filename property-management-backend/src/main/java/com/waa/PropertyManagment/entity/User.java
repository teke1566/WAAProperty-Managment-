package com.waa.PropertyManagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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





    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Offer> offers;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<FavoriteList> favoriteLists;

    @OneToMany(mappedBy = "users_id",cascade = CascadeType.ALL)
    List<Property> properties;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Message> receivedMessages;
}
