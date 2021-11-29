CREATE TABLE game_stats(
    id varchar(10) not null,
    rated varchar(6) not null,
    created_at varchar(50) not null,
    last_move_at varchar(50) not null,
    turns bigint(5) not null,
    victory_status varchar(10) not null,
    winner varchar(6) not null,
    increment_code varchar(10) not null,
    white_id varchar(20) not null,
    white_rating bigint(10) not null,
    black_id varchar(20) not null,
    black_rating bigint(10) not null,
    moves text not null,
    opening_eco varchar(4) not null,
    opening_name varchar(255) not null,
    opening_ply bigint(4) not null,
    CONSTRAINT pk_game_stats_id PRIMARY KEY(id)
 );