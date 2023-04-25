package com.waa.PropertyManagment.Service;

import com.waa.PropertyManagment.Entity.Message;
import com.waa.PropertyManagment.Entity.User;
import com.waa.PropertyManagment.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.waa.PropertyManagment.Repo.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    public MessageService( MessageRepository messageRepository, UserRepository userRepository){
        this.messageRepository=messageRepository;
        this.userRepository=userRepository;
    }

    public void sendMessage(User sender, Long recipientId, String content){
        User recipient =userRepository.findById(recipientId).orElseThrow();

        Message message= new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(content);
        messageRepository.save(message);

    }
}


