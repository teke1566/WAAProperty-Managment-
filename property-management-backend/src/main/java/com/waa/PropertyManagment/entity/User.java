package com.waa.PropertyManagment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Offer> offers;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<SavedList> savedLists;

    @OneToMany(mappedBy = "users_id",cascade = CascadeType.ALL)
    List<Property> properties;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Application> applications;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Message> receivedMessages;



    public User(Long id) {
        this.id = id;
    }

    public User() {

    }
}