package com.goapptiv.services.repositories;

import com.goapptiv.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MessageRepository extends JpaRepository<Message, Long>, QueryDslPredicateExecutor<Message> {
}
