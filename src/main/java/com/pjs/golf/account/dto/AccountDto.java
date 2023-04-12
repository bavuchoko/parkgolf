package com.pjs.golf.account.dto;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.account.entity.AccountRole;
import com.pjs.golf.account.entity.Gender;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private static ModelMapper modelMapper = new ModelMapper();

    private Integer id;

    @NotBlank(message = "아이디는 필수값입니다.")
    private String username;

    private String birth;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;

    private String name;
    private Gender gender;
    private Set<AccountRole> roles;

    private String token;

    private LocalDateTime joinDate;
    private String protrait;
    public Account toEntity() {
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper.map(this, Account.class);
    }
}

