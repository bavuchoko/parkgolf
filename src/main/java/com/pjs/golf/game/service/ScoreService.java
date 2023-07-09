package com.pjs.golf.game.service;

import java.util.List;

public interface ScoreService {
    List getScoreList(int id);

    List groupingPlayers(int id);
}
