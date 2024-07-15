package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  public Optional<User> findByEmail(String email);

  public Optional<User> findByUsername(String username);

  @Query("SELECT u FROM Institution u WHERE u.name LIKE %:search%")
  Page<User> findUsersWithFilters(@Param("search") String search, Pageable pageable);
}
