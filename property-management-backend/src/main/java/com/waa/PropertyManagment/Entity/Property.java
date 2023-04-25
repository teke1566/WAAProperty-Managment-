package com.waa.PropertyManagment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "property")
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "property_type")
    private String propertyType;
    @Column(name = "num_rooms")
    private Integer numRooms;
    @Column(name = "home-type")
    private String homeType;
    private String location;
    private Double price;
    private String status;

//    @Column(name = "owner_id")
//    @ManyToOne
//    private User users_id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User users_id;

}
