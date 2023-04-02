package com.pjs.golf.game.service.impl;

import com.pjs.golf.game.entity.City;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.MatchField;
import com.pjs.golf.game.repository.GameJapRepository;
import com.pjs.golf.game.repository.MatchFieldJpaRepository;
import com.pjs.golf.game.service.MatchFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchFieldServiceImpl  implements MatchFieldService {

    @Autowired
    MatchFieldJpaRepository matchFieldJpaRepository;
    @Override
    public List selectFieldListByCity(City city) {
        return matchFieldJpaRepository.findAllByCity(city);
    }

    @Override
    public List selectAllField() {
        return  matchFieldJpaRepository.findAll();
    }

    @Override
    public MatchField insertField(MatchField field) {
        return matchFieldJpaRepository.save(field);
    }
}
