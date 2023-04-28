package com.waa.PropertyManagment.service;


import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.entity.dto.request.PagingRequest;
import com.waa.PropertyManagment.entity.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {

    List<UserResponseDto> findAll();


    List<UserResponseDto> getAllCustomer();

    Page<User> pagination(PagingRequest pagingRequest);

    User getCustomerById(long id);

    List<UserResponseDto> getAllOwners();

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