package com.waa.PropertyManagment.domain;

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
    private  Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive ;
    private String resetPassword;
    private boolean isDeleted;



//    @OneToMany(  mappedBy = "user",fetch = FetchType.LAZY  , cascade = CascadeType.ALL)
//    private List<Property> properties;
}
