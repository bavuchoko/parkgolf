package com.pjs.golf.game.repository;

import com.pjs.golf.game.entity.Field;
import com.pjs.golf.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldJpaRepository extends JpaRepository<Field, Integer> {
}
