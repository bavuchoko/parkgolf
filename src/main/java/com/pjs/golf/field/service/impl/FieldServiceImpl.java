package com.pjs.golf.field.service.impl;

import com.pjs.golf.field.entity.Field;
import com.pjs.golf.field.repository.FieldJpaRepository;
import com.pjs.golf.field.service.FieldService;
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
