package com.pjs.golf.field.service;

import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.field.entity.Field;
import com.pjs.golf.game.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FieldService {
    Field createField(Field field);

    Page<Game> getFieldList(SearchDto search, Pageable pageable);
}
