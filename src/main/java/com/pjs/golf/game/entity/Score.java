package com.pjs.golf.game.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pjs.golf.account.entity.Account;
import com.pjs.golf.game.entity.id.ScoreId;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ScoreId.class)
public class Score {

    @Id
    @Column(name = "game_id")
    private Integer gameId;

    @Id
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "account_id")
    private Account player;

    @Id
    @Column(name = "round_id")
    private Integer roundId;

    @Column(name = "play_group")
    private String playGroup;

    private int hit;  //타수

    private int point;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime playDate;


}
