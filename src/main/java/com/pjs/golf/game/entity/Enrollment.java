package com.pjs.golf.game.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pjs.golf.game.entity.id.EnrollmentId;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EnrollmentId.class)
public class Enrollment {

    @Id
    @Column(name = "game_id")
    private Integer gameId;

    @Id
    @Column(name = "player_id")
    private Integer playerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enrollDate;
}
