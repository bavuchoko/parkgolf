package com.pjs.golf.game.entity.id;

import com.pjs.golf.account.entity.Account;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ScoreId implements Serializable {
    private static final long serialVersionUID = 1L;
    public Integer gameId;
    public Account player;
    public Integer roundId;

}
