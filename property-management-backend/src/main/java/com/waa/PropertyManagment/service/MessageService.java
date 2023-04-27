package com.waa.PropertyManagment.service;

import com.waa.PropertyManagment.entity.Message;
import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.waa.PropertyManagment.repository.MessageRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    public MessageService( MessageRepository messageRepository, UserRepository userRepository){
        this.messageRepository=messageRepository;
        this.userRepository=userRepository;
    }
/*f.Send message to the property owner:
    Create a messaging system that allows customers
    to send messages to property owners. Implement an endpoint
    to handle message creation, and save the messages
    in the database using a MessageRepository.
* */

    /*public void sendMessage(Long senderId, Message message){



        Optional<User> senderCondition =userRepository.findById(senderId);
        Optional<User> recipientCondition= userRepository.findById(message.getRecipient().getId());
        User sender=senderCondition.get();
        User recipient= recipientCondition.get();
        if (senderCondition.isPresent()&&recipientCondition.isPresent()){
        sender.setSentMessages(Collections.singletonList(message));
        messageRepository.save(message);

        } else if (senderCondition.isPresent()) {
            Message message1= new Message();
            message1.setSender(sender);
            message1.setRecipient(recipient);
            message1.setContent(message.getContent());
            message1.setSubject(message.getSubject());
            messageRepository.save(message);
        } else {
            System.out.println("No such user exist...");
        }

    }*/
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}


