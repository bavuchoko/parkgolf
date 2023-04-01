package com.pjs.golf.game.repository;

import com.pjs.golf.game.entity.City;
import com.pjs.golf.game.entity.Game;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameJapRepository extends JpaRepository<Game, Long> {

    @Override
    List<Game> findAll();


    List<Game> findAllByField_City(City city);

    List<Game> findAllByClosedAndField_City(boolean close, City city);
}
