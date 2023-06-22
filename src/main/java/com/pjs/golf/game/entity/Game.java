package com.pjs.golf.game.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pjs.golf.account.entity.Account;
import lombok.*;
import org.modelmapper.config.Configuration;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
    @Column(nullable = false)
    private LocalDateTime modifyDate;

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
}
