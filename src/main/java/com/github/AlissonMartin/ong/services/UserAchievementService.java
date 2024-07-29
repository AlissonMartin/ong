package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.models.Achievement;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.models.UserAchievement;
import com.github.AlissonMartin.ong.repositories.UserAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAchievementService {

  @Autowired
  UserAchievementRepository userAchievementRepository;

  Boolean userHasAchievement(User user, Achievement achievement) {
    return userAchievementRepository.existsByUserIdAndAchievementId(user.getId(), achievement.getId());
  }

  UserAchievement create(User user, Achievement achievement) {
    if (userHasAchievement(user, achievement) == false) {
      UserAchievement userAchievement = new UserAchievement();
      userAchievement.setUser(user);
      userAchievement.setAchievement(achievement);
      userAchievementRepository.save(userAchievement);
    }
    return null;
  }
}
