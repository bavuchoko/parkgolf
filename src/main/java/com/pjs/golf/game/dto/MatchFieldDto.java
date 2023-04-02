package com.pjs.golf.game.dto;


import com.pjs.golf.account.dto.AccountDto;
import com.pjs.golf.account.entity.Account;
import com.pjs.golf.game.entity.City;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.MatchField;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchFieldDto {

    private static ModelMapper modelMapper = new ModelMapper();
    private Integer id;

    private String address;
    private City city;
    private Account account;
    private String addressDetail;
    private String postNumber;
    private String phone;
    private String contactCrew;
    private String name;
    private String description;
    private LocalDateTime regDate;
    private boolean open;

    public MatchField toEntity() {
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper.map(this, MatchField.class);
    }
}
