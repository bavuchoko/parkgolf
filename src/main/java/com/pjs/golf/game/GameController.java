package com.pjs.golf.game;


import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.WebCommon;
import com.pjs.golf.common.annotation.CurrentUser;
import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.common.exception.NoSuchDataCustomException;
import com.pjs.golf.game.dto.GameDto;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.Score;
import com.pjs.golf.game.service.GameService;
import com.pjs.golf.game.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/game",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final ScoreService scoreService;

    private final WebCommon webCommon;

    /**
     * 목록조회
     * <pre>
     * 조회 기간이나 검색어를 받아서 해당하는 목록을 조회하는 컨트롤러
     * 조건이 없을 경우 null을 받아야 됨.
     * 검색기간의 형식은 2023-01-01T00:00:00
     * </pre>
     */
    @GetMapping
    public ResponseEntity getGameList(
            Pageable pageable,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String searchTxt,
            PagedResourcesAssembler<Game> assembler,
            @CurrentUser Account account
    ){
        SearchDto search = SearchDto.builder()
                .startDate((webCommon.localDateToLocalDateTime(startDate,"startDate")))
                .endDate((webCommon.localDateToLocalDateTime(endDate,"endDate")))
                .SearchTxt(searchTxt)
                .build();

        Page<Game> games = gameService.getGameList(search,pageable);
        var pageResources = assembler.toModel(games, entity ->
                EntityModel.of(entity)
                        .add(linkTo(GameController.class).slash(entity.getPlayDate()).withRel("query-content"))
                        .add(linkTo(GameController.class).withSelfRel())
        );
        pageResources.add(Link.of("/docs/asciidoc/index.html#create-game-api").withRel("profile"));

        return ResponseEntity.ok().body(pageResources);
    }


    /**
     * 경기 상세 조회
     * <pre>
     * 해당하는 게임의 id 값을 받아서 해당 게임의 상세내용을 조회한다.
     * @return 200 | 204
     * </pre>
     */
    @GetMapping("/{id}")
    public ResponseEntity getGameInfo(
            @PathVariable int id,
            @CurrentUser Account account){

        try {
            Game game = gameService.getGameInfo(id);
            WebMvcLinkBuilder selfLink = linkTo(GameController.class).slash(game.getId());
            EntityModel resource = EntityModel.of(game);

            resource.add(Link.of("/docs/asciidoc/index.html#create-game-api").withRel("profile"));
            resource.add(selfLink.withRel("query"));
            if (game.getOpener().equals(account)) {
                resource.add(selfLink.withRel("update"));
            }
            return ResponseEntity.ok().body(resource);
        }catch (NoSuchDataCustomException e){
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * 경기 등록
     * @return 200 | 400
     * @param gameDto GameDto
     * */

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity creatGame(
            @RequestBody GameDto gameDto,
            Errors errors,
            @CurrentUser Account account){

        if (errors.hasErrors()) {
            return webCommon.badRequest(errors, this.getClass());
        }

        gameDto.setOpener(account);
        gameDto.setCreateDate(LocalDateTime.now());
        Game game = gameDto.toEntity();
        try{
            Game savedGame = gameService.createGame(game);
            WebMvcLinkBuilder selfLink = linkTo(GameController.class).slash(game.getId());
            EntityModel resource = EntityModel.of(savedGame);
            URI uri = selfLink.toUri();

            resource.add(selfLink.withRel("self"));
            resource.add(selfLink.withRel("update"));
            resource.add(Link.of("/docs/asciidoc/api.html#").withRel("profile"));

            return ResponseEntity.created(uri).body(resource);

        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *  경기 수정
     * @return 200 | 400
     * */
    @PutMapping()
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity updateGame(
            @RequestBody GameDto gameDto,
            Errors errors,
            @CurrentUser Account account
    ){
        if (errors.hasErrors()) {
            return webCommon.badRequest(errors, this.getClass());
        }
        if(account.equals(gameDto.getOpener())) {
            gameDto.setOpener(account);
            gameDto.setModifyDate(LocalDateTime.now());
            Game game = gameDto.toEntity();
            try {
                Game updatedGame = gameService.updateGame(game);
                WebMvcLinkBuilder selfLink = linkTo(GameController.class).slash(game.getId());
                EntityModel resource = EntityModel.of(updatedGame);

                resource.add(selfLink.withRel("self"));
                resource.add(selfLink.withRel("update"));
                resource.add(Link.of("/docs/asciidoc/api.html#").withRel("profile"));

                return ResponseEntity.ok().body(resource);

            }catch (Exception e){
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/{id}/enroll")
    public ResponseEntity getPlayers(
            @PathVariable int id){

        return null;
    }

    @PostMapping("/{id}/enroll")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity enroll(
            @PathVariable int id,
            @CurrentUser Account account){
        List playerList = scoreService.enrollToGame(id, account);
        return ResponseEntity.ok(playerList);
    }



}
