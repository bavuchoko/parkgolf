package com.pjs.golf.field.repository;

import com.pjs.golf.field.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldJpaRepository extends JpaRepository<Field, Integer> {
}
