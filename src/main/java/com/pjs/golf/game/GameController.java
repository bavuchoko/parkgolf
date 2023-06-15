package com.pjs.golf.game;


import com.pjs.golf.account.entity.Account;
import com.pjs.golf.account.service.AccountService;
import com.pjs.golf.common.annotation.CurrentUser;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/game",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    @GetMapping
    public ResponseEntity loadGameList(Pageable pageable, PagedResourcesAssembler<Game> assembler){

        return null;
    }

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

            if (game.getOpener().equals(account)) {
                resource.add(selfLink.withRel("update"));
            }
            return ResponseEntity.ok().body(resource);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{id}")
    public ResponseEntity enrollToGame(@PathVariable int id){

        return null;
    }


}
