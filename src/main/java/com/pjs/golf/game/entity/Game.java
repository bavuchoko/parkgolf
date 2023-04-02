package com.pjs.golf.game.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pjs.golf.account.dto.AccountSerializer;
import com.pjs.golf.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "game_id")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="account_id")
    @JsonSerialize(using = AccountSerializer.class)
    private Account host;

    private String subject;
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="field_id")
    @JsonSerialize(using = AccountSerializer.class)
    private MatchField field;


    private LocalDateTime matchDate;

    private String description;
    @Column(nullable=false)
    private boolean closed = false;

}
