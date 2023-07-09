package com.pjs.golf.game.service.impl;

import com.pjs.golf.game.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScroeServiceImpl implements ScoreService {


    @Override
    public List getScoreList(int id) {
        return null;
    }

    @Override
    public List groupingPlayers(int id) {
        return null;
    }
}
