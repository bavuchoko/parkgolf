package com.pjs.golf.game.service;

import com.pjs.golf.game.entity.City;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.MatchField;

import java.util.List;

public interface MatchFieldService {
    List selectFieldListByCity(City city);
    List selectAllField();

    MatchField insertField(MatchField field);
}
