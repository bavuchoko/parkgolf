package com.pjs.golf.game.service.impl;

import com.pjs.golf.game.entity.City;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.repository.GameJapRepository;
import com.pjs.golf.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameJapRepository gameJapRepository;
    @Override
    public List selectGameListByCity(City city) {
        return gameJapRepository.findAllByClosedAndField_City(false, city);
    }

    @Override
    public Game insertGame(Game game) {
        return gameJapRepository.save(game);
    }

    @Override
    public List selectAll() {
        return gameJapRepository.findAll();
    }

}
