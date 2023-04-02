package com.pjs.golf.game.repository;

import com.pjs.golf.game.entity.City;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.MatchField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchFieldJpaRepository extends JpaRepository<MatchField, Integer> {

    @Override
    List<MatchField> findAll();


    List<MatchField> findAllByCity(City city);

}
