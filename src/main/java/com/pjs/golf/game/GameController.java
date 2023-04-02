package com.pjs.golf.game;

import com.pjs.golf.account.dto.AccountDto;
import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.annotation.CurrentUser;
import com.pjs.golf.game.dto.GameDto;
import com.pjs.golf.game.entity.City;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "game")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping
    public ResponseEntity getGameListByCity(City city) {
        List list = null;
        if(city==null){
            list = gameService.selectAll();
        }else{
            list = gameService.selectGameListByCity(city);
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity insertGame(
                @RequestBody @Valid GameDto gameDto,
                Errors errors,
                @CurrentUser AccountDto accountDto) {
        if (errors.hasErrors()) {
            return badRequest(errors);
        }
        gameDto.setHost(accountDto);
        gameDto.setMatchDate(LocalDateTime.now());
        City city = gameDto.getField().getCity();
        Game game = gameDto.toEntity();

        gameService.insertGame(game);
        List list = gameService.selectAll();
        return ResponseEntity.ok(list);
    }


    private ResponseEntity<EntityModel<Errors>> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(EntityModel.of(errors).add(linkTo(GameController.class).slash("/").withRel("redirect")));
    }

}
