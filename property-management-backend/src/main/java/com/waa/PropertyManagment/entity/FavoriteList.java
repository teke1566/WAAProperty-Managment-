package com.waa.PropertyManagment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "saved_list")
public class FavoriteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private User customer;


   @OneToMany(mappedBy = "favoriteList", cascade = CascadeType.ALL)
    private List<Property> properties;

}
