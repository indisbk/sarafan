package com.learning.sarafan.repository;

import com.learning.sarafan.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
