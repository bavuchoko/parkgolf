package com.pjs.golf.fields.repository;

import com.pjs.golf.fields.entity.Fields;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldsJpaRepository extends JpaRepository<Fields, Integer> {
}
