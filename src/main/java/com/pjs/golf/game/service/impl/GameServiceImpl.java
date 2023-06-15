package com.pjs.golf.game.service.impl;

import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.repository.GameJpaRepository;
import com.pjs.golf.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameJpaRepository gameJpaRepository;

    @Override
    public Game getGameInfo(int id) {
//        return gameJpaRepository.findById(id).orElseGet();
        return null;
    }
}
