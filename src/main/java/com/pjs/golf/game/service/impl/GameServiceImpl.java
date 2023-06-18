package com.pjs.golf.game.service.impl;

import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.common.exception.NoSuchDataCustomException;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.QGame;
import com.pjs.golf.game.repository.GameJpaRepository;
import com.pjs.golf.game.repository.querydsl.GameJpaQuerydslSupport;
import com.pjs.golf.game.service.GameService;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        return null;
    }


}
