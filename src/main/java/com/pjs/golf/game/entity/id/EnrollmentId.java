package com.pjs.golf.game.entity.id;

import javax.persistence.Column;
import java.io.Serializable;

public class EnrollmentId implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "game_id")
    public Integer gameId;

    @Column(name = "player_id")
    public Integer playerId;
}
