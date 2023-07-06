package com.pjs.golf.fields.service.impl;

import com.pjs.golf.fields.entity.Fields;
import com.pjs.golf.fields.repository.FieldsJpaRepository;
import com.pjs.golf.fields.repository.querydsl.FieldsJpaQuerydslSupport;
import com.pjs.golf.fields.service.FieldsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FieldsServiceImpl implements FieldsService {

    private final FieldsJpaRepository fieldsJpaRepository;
    private final FieldsJpaQuerydslSupport fieldsJpaQuerydslSupport;
    @Override
    @Transactional(readOnly = true)
    public Fields createField(Fields fields) {
        Fields result =fieldsJpaRepository.save(fields);
        return result;
    }

    @Override
    public Page<Fields> getFieldList(String city, Pageable pageable) {
        return fieldsJpaQuerydslSupport.getFieldsListByCity(city,pageable);
    }
}
