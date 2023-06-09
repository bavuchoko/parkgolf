package com.pjs.golf.game.repository.querydsl;

import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.common.jpa.QuerydslCommonMethod;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.QGame;
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
public class GameJpaQuerydslSupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManage;

    public GameJpaQuerydslSupport(JPAQueryFactory jpaQueryFactory, EntityManager entityManage) {
        super(Game.class);
        this.queryFactory = jpaQueryFactory;
        this.entityManage = entityManage;
    }
    QGame game = QGame.game;
    public Page<Game> getGameListBySearch(SearchDto search, Pageable pageable) {


        JPAQuery<Game> query= queryFactory.selectFrom(game).where(
                eqAddress(search.getSearchTxt()),
                likeDetail(search.getSearchTxt()),
                game.playDate.between(search.getStartDate(),search.getEndDate())
                )
                .orderBy(QuerydslCommonMethod.getOrderList(pageable.getSort(), Game.class).stream().toArray(OrderSpecifier[]::new))
                .limit(pageable.getPageSize())
                ;
        long totalCount = query.stream().count();
        List<Game> result = getQuerydsl().applyPagination(pageable,query).fetch();
        return new PageImpl<>(result,pageable,totalCount);
    }

//    private BooleanBuilder searchText(String searchTxt) {
//        return QuerydslNullSafeBuilder.nullSafeBuilder(()->game.address.eq(searchTxt));
//    }

    private BooleanExpression eqAddress(String address) {
        if (StringUtils.hasText(address)) return game.fields.address.eq(address);
        return null;
    }
    private BooleanExpression likeDetail(String detail) {
        if (StringUtils.hasText(detail)) return game.detail.contains(detail);
        return null;
    }
}
