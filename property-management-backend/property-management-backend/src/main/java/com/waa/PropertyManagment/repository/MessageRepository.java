package com.waa.PropertyManagment.repository;

import com.waa.PropertyManagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.waa.PropertyManagment.entity.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRecipientId(Long recipientId);
    List<Message> findBySenderAndRecipient(User sender, User recipient);
}
