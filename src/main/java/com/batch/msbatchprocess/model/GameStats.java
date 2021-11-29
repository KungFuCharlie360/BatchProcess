package com.batch.msbatchprocess.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "chess_game")
public class GameStats {
    @Id
    private String id;
    private String rated;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "last_move_at")
    private String lastMoveAt;
    private BigInteger turns;
    @Column(name = "victory_status")
    private String victoryStatus;
    private String winner;
    @Column(name = "increment_code")
    private String incrementCode;
    @Column(name = "white_id")
    private String whiteId;
    @Column(name = "white_rating")
    private BigInteger whiteRating;
    @Column(name = "black_id")
    private String blackId;
    @Column(name = "black_rating")
    private BigInteger blackRating;
    private String moves;
    @Column(name = "opening_eco")
    private String openingEco;
    @Column(name = "opening_name")
    private String openingName;
    @Column(name = "opening_ply")
    private String openingPly;
}
