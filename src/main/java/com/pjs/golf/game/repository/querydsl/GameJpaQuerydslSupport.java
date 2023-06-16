package com.pjs.golf.game.repository.querydsl;

import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.QGame;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.catalina.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class GameJpaQuerydslSupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManage;

    public GameJpaQuerydslSupport(JPAQueryFactory jpaQueryFactory, EntityManager entityManage, EntityManager entityManager) {
        super(Store.class);
        this.queryFactory = jpaQueryFactory;
        this.entityManage = entityManage;
    }
    public Page<Game> getGameListBySearch(SearchDto search, Pageable pageable) {

        QGame game = QGame.game;

        JPAQuery<Game> query= queryFactory.selectFrom(game).where(
                game.address.eq(search.getSearchTxt()),
                game.date.between(search.getStartDate(),search.getEndDate())
        );
        long totalCount = query.stream().count();
        List<Game> result = getQuerydsl().applyPagination(pageable,query).fetch();
        return new PageImpl<>(result,pageable,totalCount);
    }
}
