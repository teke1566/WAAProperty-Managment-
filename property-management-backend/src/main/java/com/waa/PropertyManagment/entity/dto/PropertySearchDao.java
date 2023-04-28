package com.waa.PropertyManagment.entity.dto;

import com.waa.PropertyManagment.entity.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PropertySearchDao {
    Property property;
    private final EntityManager em;

    public List<Property> findAllByCriteria(SearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        List<Predicate> predicates = new ArrayList<>();
        //select from property
        Root<Property> root = criteriaQuery.from(Property.class);
        //we did the join tto access the embedded(address) object attribute city
        //Join<Property, Address> address = root.join("address");
        if (searchCriteria.getPropertyType() != null) {
            Predicate propertyTypePredicate = criteriaBuilder.like(root.get("propertyType"), "%" + searchCriteria.getPropertyType() + "%");
            predicates.add(propertyTypePredicate);
        }
        if (searchCriteria.getCity() != null) {
            Predicate addressPredicate = criteriaBuilder.like(root.get("city"), "%" + searchCriteria.getCity() + "%");
            predicates.add(addressPredicate);

        }
        if (searchCriteria.getStatus() != null) {
            Predicate statusPredicate = criteriaBuilder.like(root.get("status"), "%" + searchCriteria.getStatus());
            predicates.add(statusPredicate);

        }
        if (searchCriteria.getPrice() != null) {
            Predicate pricePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), searchCriteria.getPrice());
            predicates.add(pricePredicate);
        }
        if (searchCriteria.getNumberOfRooms() != null) {
            Predicate nnumberOfRoomsPredicate = criteriaBuilder.equal(root.get("numberOfRooms"), searchCriteria.getNumberOfRooms());
            predicates.add(nnumberOfRoomsPredicate);
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Property> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }


}
