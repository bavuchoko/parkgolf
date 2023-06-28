package com.pjs.golf.game.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pjs.golf.account.entity.Account;
import lombok.*;
import org.modelmapper.config.Configuration;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "game_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account opener;

    @Column(nullable = false)
    private LocalDateTime createDate;
    @Column(nullable = true)
    private LocalDateTime modifyDate;

    private int rounding;

    @Column(nullable = false)
    private LocalDateTime playDate;
    @Column(nullable = false)
    private String address;
    private int playerCount;
    private String dayKor;
    private String detail;

    public void opener(Account opener) {
        this.opener = opener;
    }

    public void createdAt(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void updateGame(Game game) {
        this.opener = game.getOpener();
        this.modifyDate = LocalDateTime.now();
        this.playDate = game.getPlayDate();
        this.address = game.getAddress();
        this.dayKor = game.getDayKor();
        this.detail = game.getDetail();
    }

    public List<Score> enrollToGame(Game game, Account account) {
        List<Score> scores = new ArrayList<>();
        IntStream.rangeClosed(1,game.getPlayerCount()).forEach(e->
                scores.add(Score.builder()
                        .gameId(game.getId())
                        .player(account)
                        .roundId(e)
                        .build()
                )
        );
        return scores;
    }
}
