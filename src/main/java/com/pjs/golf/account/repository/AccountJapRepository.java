package com.pjs.golf.account.repository;

import com.pjs.golf.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJapRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
