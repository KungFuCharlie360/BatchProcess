package com.batch.msbatchprocess.processor;


import com.batch.msbatchprocess.model.GameStats;
import com.batch.msbatchprocess.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class GameStatsProcessor implements ItemProcessor<GameStats, GameStats> {
    @Autowired
    GameRepository gameRepository;

    @Override
    public GameStats process(GameStats gameStats) throws Exception {
        log.info(gameStats.toString());
        gameRepository.findById(gameStats.getId()).map(existingGame -> {
            log.info("At processor -- found existing game");
            return existingGame;
        }).orElseGet(()-> {
            log.info("At processor -- no new game found");
            return null;
        });
        return gameStats;
    }
}
