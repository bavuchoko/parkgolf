package com.pjs.golf.game.service.impl;

import com.pjs.golf.game.repository.GameJpaRepository;
import com.pjs.golf.score.repository.ScoreJpaRepository;
import com.pjs.golf.score.repository.querydsl.ScroeJpaQuerydslSupport;
import com.pjs.golf.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScroeServiceImpl implements ScoreService {

    private final ScoreJpaRepository scoreJpaRepository;
    private final ScroeJpaQuerydslSupport scroeJpaQuerydslSupport;
    private final GameJpaRepository gameJpaRepository;


}
