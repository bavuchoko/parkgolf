package com.pjs.golf.game.entity.id;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
public class ScoreId implements Serializable {

    private static final long serialVersionUID = 5735022999922182249L;

    @Column(name = "game_id")
    public Integer gameId;

    @Column(name = "player_id")
    public Integer playerId;

    @Column(name = "round_id")
    private Integer roundId;

}
