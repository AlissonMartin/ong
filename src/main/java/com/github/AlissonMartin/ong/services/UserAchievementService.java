package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.enums.Criteria;
import com.github.AlissonMartin.ong.models.Achievement;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.models.UserAchievement;
import com.github.AlissonMartin.ong.repositories.AchievementRepository;
import com.github.AlissonMartin.ong.repositories.UserAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAchievementService {

  private UserAchievementRepository userAchievementRepository;
  private AchievementRepository achievementRepository;

  public UserAchievementService(UserAchievementRepository userAchievementRepository, AchievementRepository achievementRepository) {
    this.userAchievementRepository = userAchievementRepository;
    this.achievementRepository = achievementRepository;
  }

  Boolean userHasAchievement(User user, Achievement achievement) {
    return userAchievementRepository.existsByUserIdAndAchievementId(user.getId(), achievement.getId());
  }

  public UserAchievement FullProfile(User user) {
    if (user.isComplete()) {
      Achievement achievement = achievementRepository.findByCriteria(Criteria.FULL_PROFILE);
      return create(user, achievement);
    }
    return null;
  }

  public UserAchievement FirstChosenApply(User user) {
    // Verifica se o usuário já tem algum JobApplication com status CHOSEN
    boolean hasChosen = user.getJobApplications() != null &&
      user.getJobApplications().stream().anyMatch(app -> app.getStatus() != null && app.getStatus().name().equals("CHOSEN"));
    if (!hasChosen) {
      Achievement achievement = achievementRepository.findByCriteria(Criteria.FIRSTCHOSENAPPLY);
      return create(user, achievement);
    }
    return null;
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
