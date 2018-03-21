package com.goapptiv.services.repositories;

import com.goapptiv.entities.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long>, QueryDslPredicateExecutor<MessageTemplate> {
}
