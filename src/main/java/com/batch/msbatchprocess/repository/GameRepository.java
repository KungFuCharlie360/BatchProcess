package com.batch.msbatchprocess.repository;

import com.batch.msbatchprocess.model.GameStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameStats,String> {
    @Override
    Optional<GameStats> findById(String id);
}
