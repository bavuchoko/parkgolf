package com.pjs.golf.game.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pjs.golf.account.dto.AccountSerializer;
import com.pjs.golf.account.entity.Account;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.MatchField;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private static ModelMapper modelMapper = new ModelMapper();
    private Integer id;

    private Account host;

    private MatchField field;


    private LocalDateTime matchDate;

    private String description;

    public Game toEntity() {
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper.map(this, Game.class);
    }

}
