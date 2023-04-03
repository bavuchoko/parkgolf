package com.pjs.golf.game.dto;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.game.entity.Game;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollDto {
    private Long id;

    private Game game;

    private Account account;

    private int enrollSequence; //등록 순서
    private int group;          //전체 참가자 중에서 랜덤으로 편성한 조
    private int order;
}
