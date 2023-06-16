package com.pjs.golf.game.service;

import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.game.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameService {

    Game getGameInfo(int id);


    Page<Game> getGameList(SearchDto search, Pageable pageable);
}
