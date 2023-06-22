package com.pjs.golf.game.service.impl;

import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.common.exception.NoSuchDataCustomException;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.repository.GameJpaRepository;
import com.pjs.golf.game.repository.querydsl.GameJpaQuerydslSupport;
import com.pjs.golf.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameJpaRepository gameJpaRepository;
    private final GameJpaQuerydslSupport gameJpaQuerydslSupport;

    @Override
    public Game getGameInfo(int id) {
        return gameJpaRepository.findById(id).orElseThrow(()->new NoSuchDataCustomException("해당 데이터 없음"));
    }


    @Override
    public Page<Game> getGameList(SearchDto search, Pageable pageable) {
        return  gameJpaQuerydslSupport.getGameListBySearch(search, pageable);
    }

    @Override
    public Game createGame(Game game) {
        return gameJpaRepository.save(game);
    }

    @Override
    public Game updateGame(Game game) {
        return null;
    }


}
