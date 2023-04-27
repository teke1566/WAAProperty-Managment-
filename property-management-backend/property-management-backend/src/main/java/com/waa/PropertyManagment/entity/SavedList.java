package com.waa.PropertyManagment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "saved_list")
public class SavedList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User customer;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property properties;
}
