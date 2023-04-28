package com.waa.PropertyManagment.service.impl;

import com.waa.PropertyManagment.entity.Message;
import com.waa.PropertyManagment.repository.MessageRepository;
import com.waa.PropertyManagment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository){
        this.messageRepository=messageRepository;
        this.userRepository=userRepository;
    }
/*f.Send message to the property owner:
    Create a messaging system that allows customers
    to send messages to property owners. Implement an endpoint
    to handle message creation, and save the messages
    in the database using a MessageRepository.
* */

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }


    public List<Message> getReceivedMessages(Long recipientId) {
        return messageRepository.findByRecipientId(recipientId);
    }

    public List<Message> getSentMessages(Long senderId) {
        return messageRepository.findBySenderId(senderId);
    }

    public Message getMessageById(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid message Id:" + messageId));

        return message;
    }

    public void deleteMessage(Long messageId) {
        Message message = getMessageById(messageId);
        messageRepository.delete(message);
    }
}


