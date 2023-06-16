package com.pjs.golf.game.repository.querydsl;

import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.QGame;
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
public class GameJpaQuerydslSupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManage;

    public GameJpaQuerydslSupport(JPAQueryFactory jpaQueryFactory, EntityManager entityManage, EntityManager entityManager) {
        super(Store.class);
        this.queryFactory = jpaQueryFactory;
        this.entityManage = entityManage;
    }
    QGame game = QGame.game;
    public Page<Game> getGameListBySearch(SearchDto search, Pageable pageable) {


        JPAQuery<Game> query= queryFactory.selectFrom(game).where(
                eqAddress(search.getSearchTxt()),
                likeDetail(search.getSearchTxt()),
                game.date.between(search.getStartDate(),search.getEndDate())
        );
        long totalCount = query.stream().count();
        List<Game> result = getQuerydsl().applyPagination(pageable,query).fetch();
        return new PageImpl<>(result,pageable,totalCount);
    }

//    private BooleanBuilder searchText(String searchTxt) {
//        return QuerydslNullSafeBuilder.nullSafeBuilder(()->game.address.eq(searchTxt));
//    }

    private BooleanExpression eqAddress(String address) {
        if (StringUtils.hasText(address)) return game.address.eq(address);
        return null;
    }
    private BooleanExpression likeDetail(String detail) {
        if (StringUtils.hasText(detail)) return game.address.contains(detail);
        return null;
    }
}
