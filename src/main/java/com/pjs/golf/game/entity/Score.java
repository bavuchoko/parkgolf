package com.pjs.golf.game.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pjs.golf.game.entity.id.ScoreId;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ScoreId.class)
public class Score {

    @Id
    @Column(name = "game_id")
    private Integer gameId;

    @Id
    @Column(name = "player_id")
    private Integer playerId;

    @Id
    @Column(name = "round_id")
    private Integer roundId;

    @Column(name = "play_group")
    private String playGroup;

    private int hit;  //타수

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime playDate;
}
