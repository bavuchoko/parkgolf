package com.pjs.golf.game.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pjs.golf.account.entity.Account;
import com.pjs.golf.game.entity.id.ScoreId;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ScoreId.class)
public class Score {

    @Id
    @OneToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private Game game;

    @Id
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "account_id")
    private Account player;

    @Id
    @Column(name = "round_id")
    private Integer roundId;

    private int hole;

    @Column(name = "play_group")
    private String playGroup;

    private int hit;  //타수

    private int point;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime playDate;


}
