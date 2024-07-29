package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.enums.Criteria;
import com.github.AlissonMartin.ong.models.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
    Achievement findByCriteria(Criteria criteria);
}
