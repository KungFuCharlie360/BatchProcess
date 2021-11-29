package com.batch.msbatchprocess.repository;

import com.batch.msbatchprocess.model.GameStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameStats,String> {

//    String query = "INSERT INTO game_stats (" + ApplicationConstants.ID +
//            ApplicationConstants.RATED + "," +
//            ApplicationConstants.CREATEDAT + "," +
//            ApplicationConstants.LATSMOVEAT + "," +
//            ApplicationConstants.TURNS + "," +
//            ApplicationConstants.VICTORY_STATUS +  "," +
//            ApplicationConstants.WINNER +  "," +
//            ApplicationConstants.INCREMENTMODE +  "," +
//            ApplicationConstants.WHITEID +  "," +
//            ApplicationConstants.WHITERATING +  "," +
//            ApplicationConstants.BLACKID +  "," +
//            ApplicationConstants.BLACKRATING +  "," +
//            ApplicationConstants.MOVES + "," +
//            ApplicationConstants.OPENINGECO + "," +
//            ApplicationConstants.OPENINGNAME + "," +
//            ApplicationConstants.OPENINGPLY +
//            ") VALUES(:id, :rated, :createdAt, :lastMoveAt, :turns, :victoryStatus, :winner, :incrementCode, :whiteId, :whiteRating, :blackId, :blackRating, :moves, :openingEco, :openingName, :openingPly)";
//
//    @Query(value = query)
//    void insertGameStatsToTable();
}
