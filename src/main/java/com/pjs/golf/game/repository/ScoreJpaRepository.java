package com.pjs.golf.game.repository;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.Score;
import com.pjs.golf.game.entity.id.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreJpaRepository extends JpaRepository<Score, ScoreId> {

}
