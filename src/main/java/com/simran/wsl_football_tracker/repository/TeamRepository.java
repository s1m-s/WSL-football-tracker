package com.simran.wsl_football_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.simran.wsl_football_tracker.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
