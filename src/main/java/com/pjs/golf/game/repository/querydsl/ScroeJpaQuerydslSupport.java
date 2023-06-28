package com.pjs.golf.game.repository.querydsl;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.common.jpa.QuerydslCommonMethod;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.QGame;
import com.pjs.golf.game.entity.QScore;
import com.pjs.golf.game.entity.Score;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.catalina.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ScroeJpaQuerydslSupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManage;

    public ScroeJpaQuerydslSupport(JPAQueryFactory jpaQueryFactory, EntityManager entityManage, EntityManager entityManager) {
        super(Store.class);
        this.queryFactory = jpaQueryFactory;
        this.entityManage = entityManage;
    }
    QScore score = QScore.score;
    public List<Score> getGameListWhereGameGroupbyPlayer(Game game, Account account) {
        JPAQuery<Score> query= queryFactory.selectFrom(score).where(
                score.gameId.eq(game.getId())
                )
                .groupBy(score.player)
                ;
        return query.fetch();
    }

}
