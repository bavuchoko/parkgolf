package com.pjs.golf.game.service;

import com.pjs.golf.game.entity.Game;
import org.springframework.http.ResponseEntity;

public interface GameService {

    Game getGameInfo(int id);

}
