package com.pjs.golf.game.service.impl;

import com.pjs.golf.game.entity.Field;
import com.pjs.golf.game.repository.FieldJpaRepository;
import com.pjs.golf.game.repository.GameJpaRepository;
import com.pjs.golf.game.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldJpaRepository fieldJpaRepository;
    @Override
    public Field createGame(Field field) {
        return null;
    }
}
