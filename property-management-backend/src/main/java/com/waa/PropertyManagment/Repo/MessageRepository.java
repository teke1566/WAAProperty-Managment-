package com.waa.PropertyManagment.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.waa.PropertyManagment.Entity.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
