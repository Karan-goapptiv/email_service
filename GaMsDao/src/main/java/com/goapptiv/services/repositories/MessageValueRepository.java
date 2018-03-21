package com.goapptiv.services.repositories;

import com.goapptiv.entities.MessageValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface MessageValueRepository  extends JpaRepository<MessageValue, Long>, QueryDslPredicateExecutor<MessageValue> {
}
