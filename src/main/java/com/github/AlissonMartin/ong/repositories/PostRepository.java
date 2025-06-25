package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByInstitution(Institution institution);
}
