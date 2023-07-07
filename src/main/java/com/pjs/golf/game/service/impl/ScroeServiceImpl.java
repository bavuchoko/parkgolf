package com.pjs.golf.game.service.impl;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.exception.NoSuchDataCustomException;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.Score;
import com.pjs.golf.game.repository.GameJpaRepository;
import com.pjs.golf.game.repository.ScoreJpaRepository;
import com.pjs.golf.game.repository.querydsl.ScroeJpaQuerydslSupport;
import com.pjs.golf.game.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScroeServiceImpl implements ScoreService {

    private final ScoreJpaRepository scoreJpaRepository;
    private final ScroeJpaQuerydslSupport scroeJpaQuerydslSupport;
    private final GameJpaRepository gameJpaRepository;


}
