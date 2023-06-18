package com.pjs.golf.game.service;

import com.pjs.golf.common.dto.SearchDto;
import com.pjs.golf.game.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameService {

    Game getGameInfo(int id);

    /**
     * 목록조회
     * @param search SearchDto
     * @param pageable Pageable
     * */
    Page<Game> getGameList(SearchDto search, Pageable pageable);

    Game createGame(Game game);
}
