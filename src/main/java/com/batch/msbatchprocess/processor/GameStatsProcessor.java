package com.batch.msbatchprocess.processor;


import com.batch.msbatchprocess.model.GameStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class GameStatsProcessor implements ItemProcessor<GameStats, GameStats> {

    @Override
    public GameStats process(GameStats gameStats) throws Exception {
        log.info("At processor");
        return GameStats.builder()
                .id(gameStats.getId())
                .rated(gameStats.getRated())
                .createdAt(gameStats.getCreatedAt())
                .lastMoveAt(gameStats.getLastMoveAt())
                .turns(gameStats.getTurns())
                .victoryStatus(gameStats.getVictoryStatus())
                .winner(gameStats.getWinner())
                .incrementCode(gameStats.getIncrementCode())
                .whiteId(gameStats.getWhiteId())
                .whiteRating(gameStats.getWhiteRating())
                .blackId(gameStats.getBlackId())
                .blackRating(gameStats.getBlackRating())
                .moves(gameStats.getMoves())
                .openingEco(gameStats.getOpeningEco())
                .openingName(gameStats.getOpeningName())
                .openingPly(gameStats.getOpeningPly())
                .build();
    }
}
