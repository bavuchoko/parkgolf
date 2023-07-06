package com.pjs.golf.game.entity;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.fields.entity.Fields;
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
@EqualsAndHashCode(of="id")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "game_id")
    private Integer id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "opener_id")
    private Account opener;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Fields fields;

    @Column(nullable = false)
    private LocalDateTime createDate;
    @Column(nullable = true)
    private LocalDateTime modifyDate;
    @Column(nullable = false)
    private LocalDateTime playDate;

    @OneToMany
    private List<Account> players;

    private int rounding;
    private int playerCount;
    private String dayKor;
    private String detail;

    public void updateGame(Game game) {
        this.opener = game.getOpener();
        this.modifyDate = LocalDateTime.now();
        this.playDate = game.getPlayDate();
        this.dayKor = game.getDayKor();
        this.detail = game.getDetail();
    }

    public List<Score> enrollToGame(Game game, Account account) {
        List<Score> scores = new ArrayList<>();
        IntStream.rangeClosed(1,game.getPlayerCount()).forEach(e->
                scores.add(Score.builder()
                        .gameId(game.getId())
                        .player(account)
                        .build()
                )
        );
        return scores;
    }
}
