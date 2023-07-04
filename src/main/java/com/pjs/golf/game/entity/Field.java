package com.pjs.golf.game.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "field_id")
    private Integer id;

    private String address;

    private int holes;
}
