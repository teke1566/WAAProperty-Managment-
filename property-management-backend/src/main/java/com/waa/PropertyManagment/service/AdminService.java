package com.waa.PropertyManagment.service;


import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.entity.dto.request.PagingRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {

    List<User> getAllCustomer();

    Page<User> pagination(PagingRequest pagingRequest);

    User getCustomerById(long id);

    List<User> getAllOwners();

    User getOwnerById(long id);

    void saveUser(User user);

    void activateUser(long id);

    void blockUser(long id);

    void deleteUser(long id);

    List<Property> getAllProperty();

    Property getPropertyById(long id);

    void deleteProperty(long id);

    void resetPassword(long id);
}