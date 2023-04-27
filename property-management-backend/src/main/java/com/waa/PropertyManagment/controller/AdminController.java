package com.waa.PropertyManagment.controller;

import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private final CustomerService customerService;

    public AdminController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<User> getAllCustomer(){

        return customerService.getAllCustomers();
    }

}
