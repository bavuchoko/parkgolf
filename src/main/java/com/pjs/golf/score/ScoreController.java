package com.pjs.golf.score;


import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/score",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class ScoreController {
}
