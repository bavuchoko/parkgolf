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
public class MatchField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "field_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private City city;
    private String address;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="account_id")
    @JsonSerialize(using = AccountSerializer.class)
    private Account account;

    private String addressDetail;
    private String postNumber;
    private String phone;
    private String contactCrew;
    private String name;
    private String description;
    private LocalDateTime regDate;
    private boolean open;

}
