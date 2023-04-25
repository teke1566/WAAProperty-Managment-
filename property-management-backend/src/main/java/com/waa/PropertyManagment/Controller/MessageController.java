package com.waa.PropertyManagment.Controller;

import com.waa.PropertyManagment.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    // Message management operations for customers and owners
    // e.g., Send message, Read messages
}