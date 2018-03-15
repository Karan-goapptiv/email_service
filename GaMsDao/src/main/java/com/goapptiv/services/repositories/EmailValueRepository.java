package com.goapptiv.services.repositories;

import com.goapptiv.entities.EmailValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface EmailValueRepository extends JpaRepository<EmailValue, Long>, QueryDslPredicateExecutor<EmailValue> {
}
