package com.pjs.golf.field;

import com.pjs.golf.account.entity.Account;
import com.pjs.golf.common.WebCommon;
import com.pjs.golf.common.annotation.CurrentUser;
import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.field.dto.FieldDto;
import com.pjs.golf.field.entity.Field;
import com.pjs.golf.field.service.FieldService;
import com.pjs.golf.game.GameController;
import com.pjs.golf.game.dto.GameDto;
import com.pjs.golf.game.entity.Game;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/field",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;
    private final WebCommon webCommon;
    @GetMapping
    public ResponseEntity getGameList(
            Pageable pageable,
            @RequestParam(required = false) String city,
            PagedResourcesAssembler<Game> assembler
    ){
        SearchDto search = SearchDto.builder()
                .build();

        Page<Game> games = fieldService.getFieldList(search,pageable);
        var pageResources = assembler.toModel(games, entity ->
                EntityModel.of(entity)
                        .add(linkTo(FieldController.class).slash(entity.getPlayDate()).withRel("query-content"))
                        .add(linkTo(FieldController.class).withSelfRel())
        );
        pageResources.add(Link.of("/docs/asciidoc/index.html#create-game-api").withRel("profile"));

        return ResponseEntity.ok().body(pageResources);
    }


    /**
     * 필드 등록
     * @return 200 | 400
     * @param fieldDto FieldDto
     * */

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity creatGame(
            @RequestBody FieldDto fieldDto,
            Errors errors,
            @CurrentUser Account account){

        if (errors.hasErrors()) {
            return webCommon.badRequest(errors, this.getClass());
        }

        fieldDto.setRegister(account);
        Field field = fieldDto.toEntity();
        try{
            Field savedField = fieldService.createField(field);
            WebMvcLinkBuilder selfLink = linkTo(FieldController.class).slash(field.getId());
            EntityModel resource = EntityModel.of(savedField);
            URI uri = selfLink.toUri();

            resource.add(selfLink.withRel("self"));
            resource.add(selfLink.withRel("update"));
            resource.add(Link.of("/docs/asciidoc/api.html#").withRel("profile"));

            return ResponseEntity.created(uri).body(resource);

        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
