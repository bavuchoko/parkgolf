package com.pjs.golf.game.service;

import com.pjs.golf.game.entity.City;
import com.pjs.golf.game.entity.Game;

import java.util.List;

public interface GameService {

    List selectGameListByCity(City city);

    Game insertGame(Game game);

    List selectAll();
}
