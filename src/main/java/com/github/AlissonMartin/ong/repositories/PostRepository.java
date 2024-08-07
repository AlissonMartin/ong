package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
