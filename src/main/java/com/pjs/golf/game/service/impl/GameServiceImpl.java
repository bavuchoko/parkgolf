package com.pjs.golf.game.service.impl;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.common.exception.AlreadyExistSuchDataCustomException;
import com.pjs.golf.common.exception.InCorrectStatusCustomException;
import com.pjs.golf.common.exception.NoSuchDataCustomException;
import com.pjs.golf.common.exception.PermissionLimitedCustomException;
import com.pjs.golf.game.GameController;
import com.pjs.golf.game.dto.GameDto;
import com.pjs.golf.game.dto.GameStatus;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.repository.GameJpaRepository;
import com.pjs.golf.game.repository.querydsl.GameJpaQuerydslSupport;
import com.pjs.golf.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameJpaRepository gameJpaRepository;
    private final GameJpaQuerydslSupport gameJpaQuerydslSupport;

    @Override
    public Game getGameInfo(int id) {
        return gameJpaRepository.findById(id).orElseThrow(()->new NoSuchDataCustomException("해당 데이터 없음"));
    }


    @Override
    public Page<Game> getGameList(SearchDto search, Pageable pageable) {
        return  gameJpaQuerydslSupport.getGameListBySearch(search, pageable);
    }

    @Override
    public Game createGame(GameDto gameDto, Account account) {
        gameDto.setOpener(account);
        gameDto.setCreateDate(LocalDateTime.now());
        gameDto.whatIsDay(gameDto.getPlayDate());
        gameDto.setStatus(GameStatus.OPEN);
        Game game = gameDto.toEntity();
        return gameJpaRepository.save(game);
    }

    @Override
    @Transactional
    public Game updateGame(GameDto gameDto, Account account) {
        Game game = gameJpaRepository.findById(gameDto.getId()).orElseThrow( ()->new NoSuchDataCustomException() );

        if(!game.getOpener().equals(account)) throw new PermissionLimitedCustomException("권한이 없습니다.");

        game.updateGame(gameDto);

        return game;
    }

    @Override
    public CollectionModel getPageReesources(PagedResourcesAssembler<Game> assembler, Page<Game> games) {
        return assembler.toModel(games, entity ->
                EntityModel.of(entity)
                        .add(linkTo(GameController.class).slash(entity.getPlayDate()).withRel("query-content"))
                        .add(linkTo(GameController.class).withSelfRel())
        ).add(Link.of("/docs/asciidoc/index.html#create-game-api").withRel("profile"));
    }

    @Override
    public EntityModel getPageReesource(Game game, Account account) {

        WebMvcLinkBuilder selfLink = linkTo(GameController.class).slash(game.getId());
        EntityModel resource = EntityModel.of(game);

        resource.add(Link.of("/docs/asciidoc/index.html#create-game-api").withRel("profile"));
        resource.add(selfLink.withRel("query-content"));

        if (game.getOpener().equals(account)) {
            resource.add(selfLink.withRel("update-content"));
        }
        return resource;
    }

    @Override
    @Transactional
    public Game enrollGame(int id, Account account) {
        Game game= gameJpaRepository.findById(id).orElseThrow(()->new NoSuchDataCustomException() );
        if(game.getStatus().equals(GameStatus.OPEN)) {
            List players = game.getPlayers();
            players.stream().filter(player -> player.equals(account))
                    .findFirst()
                    .ifPresent(p -> {
                        throw new AlreadyExistSuchDataCustomException("이미 가입 신청되었습니다.");
                    });

            game.enrollGame(game, account);
        }else {
            throw new InCorrectStatusCustomException("요청을 처리할 수 없습니다.");
        }
        return game;
    }

    @Override
    @Transactional
    public Game updateFowrdStatusGame(int id, Account account, GameStatus gameStatus) {
        Game game= gameJpaRepository.findById(id).orElseThrow(()->new NoSuchDataCustomException() );
        if(game.getStatus().isForwardStep(gameStatus)) {
            if(!game.getOpener().equals(account)) throw new PermissionLimitedCustomException("권한이 없습니다.");
            game.updateStat(gameStatus);
        }else {
            throw new InCorrectStatusCustomException("요청을 처리할 수 없습니다.");
        }
        return game;
    }


}
