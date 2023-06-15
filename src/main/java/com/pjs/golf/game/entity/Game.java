package com.pjs.golf.game.entity;

import com.pjs.golf.account.entity.Account;
import lombok.*;
import org.modelmapper.config.Configuration;

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

    private LocalDateTime date;
    private String address;
    private int playerCount;
    private String day;
    private String detail;


}
