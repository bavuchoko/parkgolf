package com.pjs.golf.score.repository;

import com.pjs.golf.score.entity.Score;
import com.pjs.golf.score.entity.id.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreJpaRepository extends JpaRepository<Score, ScoreId> {

}
