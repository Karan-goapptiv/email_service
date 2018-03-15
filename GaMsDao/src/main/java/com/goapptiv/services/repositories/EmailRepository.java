package com.goapptiv.services.repositories;

import com.goapptiv.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface EmailRepository extends JpaRepository<Email, Long>, QueryDslPredicateExecutor<Email> {
}
