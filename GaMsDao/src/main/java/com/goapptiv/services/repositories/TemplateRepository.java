package com.goapptiv.services.repositories;

import com.goapptiv.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface TemplateRepository extends JpaRepository<Template, Long>, QueryDslPredicateExecutor<Template> {
}
