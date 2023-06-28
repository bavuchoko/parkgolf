package com.pjs.golf.game.dto;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.ModelMapperUtils;
import com.pjs.golf.game.entity.Game;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private Integer id;

    private Account opener;

    private LocalDateTime createDate;

    @NotNull
    private LocalDateTime playDate;

    private LocalDateTime modifyDate;
    private int rounding;
    @NotNull
    private String address;
    private int playerCount;
    private String dayKor;
    private String detail;

    public Game toEntity() {
        return ModelMapperUtils.getModelMapper().map(this, Game.class);
    }
}
