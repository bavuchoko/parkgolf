package com.pjs.golf.game;

import com.pjs.golf.account.dto.AccountDto;
import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.annotation.CurrentUser;
import com.pjs.golf.game.dto.GameDto;
import com.pjs.golf.game.dto.MatchFieldDto;
import com.pjs.golf.game.entity.City;
import com.pjs.golf.game.entity.Game;
import com.pjs.golf.game.entity.MatchField;
import com.pjs.golf.game.service.MatchFieldService;
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
@RequestMapping(value = "field")
public class MatchFieldController {

    @Autowired
    MatchFieldService matchFieldService;

    @GetMapping
    public ResponseEntity getGameListByCity(City city) {
        List list = null;
        if(city==null){
            list = matchFieldService.selectAllField();
        }else{
            list = matchFieldService.selectFieldListByCity(city);
        }

        return ResponseEntity.ok(list);
    }
    @PostMapping
    public ResponseEntity insertField(
            @Valid @RequestBody MatchFieldDto matchFieldDto,
            Errors errors,
            @CurrentUser Account account) {
        if (errors.hasErrors()) {
            return badRequest(errors);
        }
        matchFieldDto.setRegDate(LocalDateTime.now());
        matchFieldDto.setAccount(account);
        MatchField matchField = matchFieldDto.toEntity();
        City city = matchFieldDto.getCity();
        matchFieldService.insertField(matchField);
        List list = matchFieldService.selectFieldListByCity(city);
        return ResponseEntity.ok(list);
    }
    private ResponseEntity<EntityModel<Errors>> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(EntityModel.of(errors).add(linkTo(MatchFieldController.class).slash("/").withRel("redirect")));
    }
}
