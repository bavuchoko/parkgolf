package com.pjs.golf.game;


import com.pjs.golf.account.entity.Account;
import com.pjs.golf.game.entity.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/game",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class GameController {

    @GetMapping
    public ResponseEntity loadGameList(Pageable pageable, PagedResourcesAssembler<Game> assembler){

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity getGameInfo(@PathVariable int id){

        return null;
    }


    @PostMapping("/{id}")
    public ResponseEntity enrollToGame(@PathVariable int id){

        return null;
    }


}
