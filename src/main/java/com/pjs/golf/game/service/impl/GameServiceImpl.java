package com.pjs.golf.game.service.impl;

import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.repository.GameJpaRepository;
import com.pjs.golf.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameJpaRepository gameJpaRepository;

    @Override
    public Game getGameInfo(int id) {
        return gameJpaRepository.findById(id).orElseThrow(()->new NoSuchElementException());
    }


}
