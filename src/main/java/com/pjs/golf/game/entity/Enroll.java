package com.pjs.golf.game.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pjs.golf.account.dto.AccountSerializer;
import com.pjs.golf.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Enroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "game_id")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="account_id")
    @JsonSerialize(using = AccountSerializer.class)
    private Account account;

    private int enrollSequence; //등록 순서
    private int group;          //전체 참가자 중에서 랜덤으로 편성한 조
    private int order;          //그룹 내에서 랜덤으로 돌린 순서

}
