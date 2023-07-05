package com.pjs.golf.field.dto;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.ModelMapperUtils;
import com.pjs.golf.field.entity.Field;
import com.pjs.golf.game.entity.Game;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto {

    private Integer id;

    @NotNull
    private Account register;

    @NotNull
    private String name;
    private String city;

    @NotNull
    private String address;

    private int holes;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    public Field toEntity() {
        return ModelMapperUtils.getModelMapper().map(this, Field.class);
    }
}
