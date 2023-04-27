package com.waa.PropertyManagment.service;

import com.waa.PropertyManagment.entity.Message;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.waa.PropertyManagment.repository.MessageRepository;

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

    public void sendMessage(Long senderId, Long recipientId, String content, String subject){
        User sender =userRepository.findById(senderId).get();
        User recipient =userRepository.findById(recipientId).get();
        Message message= new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(content);
        message.setSubject(subject);
        messageRepository.save(message);
    }
}


