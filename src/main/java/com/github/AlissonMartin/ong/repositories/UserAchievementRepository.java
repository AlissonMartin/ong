package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.models.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAchievementRepository extends JpaRepository<UserAchievement, Integer> {
  boolean existsByUserIdAndAchievementId(int userId, int achievementId);
}
