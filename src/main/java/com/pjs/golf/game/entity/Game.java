package com.pjs.golf.game.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pjs.golf.account.entity.Account;
import lombok.*;
import org.modelmapper.config.Configuration;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
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

    private LocalDateTime date;
    private String address;
    private int playerCount;
    private String day;
    private String detail;


}
