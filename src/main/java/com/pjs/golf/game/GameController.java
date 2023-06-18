package com.pjs.golf.game;


import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.WebCommon;
import com.pjs.golf.common.annotation.CurrentUser;
import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.common.exception.NoSuchDataCustomException;
import com.pjs.golf.game.dto.GameDto;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/game",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;


    /**
     * 목록조회
     * <pre>
     * 조회 기간이나 검색어를 받아서 해당하는 목록을 조회하는 컨트롤러
     * 조건이 없을 경우 null을 받아야 됨.
     * 검색기간의 형식은 2023-01-01T00:00:00
     * </pre>
     */
    @GetMapping
    public ResponseEntity loadGameList(
            Pageable pageable,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String searchTxt,
            PagedResourcesAssembler<Game> assembler,
            @CurrentUser Account account
    ){
        SearchDto search = SearchDto.builder()
                .startDate(LocalDateTime.parse(startDate))
                .endDate(LocalDateTime.parse(endDate))
                .SearchTxt(searchTxt)
                .build();

        Page<Game> games = gameService.getGameList(search,pageable);
        var pageResources = assembler.toModel(games, entity -> EntityModel.of(entity).add(linkTo(GameController.class).slash(entity.getPlayDate()).withRel("query-content")));

        //        pageResources.add(Link.of("/docs/ascidoc/api.html").withRel("profile"));
        return ResponseEntity.ok().body(pageResources);
    }


    /**
     * 상세조회
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

            // TODO: 2023-06-15  api문서 링크
            //       resource.add(Link.of("/docs/asciidoc/game/api.html#").withRel("profile"));
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
     * */

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity creatGame(
            @PathVariable int id,
            @RequestBody GameDto gameDto,
            Errors errors,
            @CurrentUser Account account){

        if (errors.hasErrors()) {
            return WebCommon.badRequest(errors, this.getClass());
        }

        gameDto.setOpener(account);
        gameDto.setCreateDate(LocalDateTime.now());
        Game game = gameDto.toEntity(gameDto);
        try{
            Game savedGame = gameService.createGame(game);
            WebMvcLinkBuilder selfLink = linkTo(GameController.class).slash(game.getId());
            EntityModel resource = EntityModel.of(savedGame);

            resource.add(selfLink.withRel("query"));
            if (game.getOpener().equals(account)) {
                resource.add(selfLink.withRel("update"));
            }
            return ResponseEntity.ok().body(resource);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity updateGame(@PathVariable int id){

        return null;
    }


    @GetMapping("/{id}/enroll")
    public ResponseEntity getPlayers(@PathVariable int id){

        return null;
    }

    @PostMapping("/{id}/enroll")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity enroll(@PathVariable int id){

        return null;
    }



}
