package com.pjs.golf.fields.service.impl;

import com.pjs.golf.fields.entity.Fields;
import com.pjs.golf.fields.repository.FieldsJpaRepository;
import com.pjs.golf.fields.repository.querydsl.FieldsJpaQuerydslSupport;
import com.pjs.golf.fields.service.FieldsService;
import com.pjs.golf.game.entity.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FieldsServiceImpl implements FieldsService {

    private final FieldsJpaRepository fieldsJpaRepository;
    private final FieldsJpaQuerydslSupport fieldsJpaQuerydslSupport;
    @Override
    public Fields createField(Fields fields) {
        return fieldsJpaRepository.save(fields);
    }

    @Override
    public Page<Fields> getFieldList(String city, Pageable pageable) {
        return fieldsJpaQuerydslSupport.getFieldsListByCity(city,pageable);
    }
}
