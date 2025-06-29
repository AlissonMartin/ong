package com.github.AlissonMartin.ong.controllers;

import com.github.AlissonMartin.ong.dtos.PostResponseDTO;
import com.github.AlissonMartin.ong.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PostsController {
    @Autowired
    private PostService postService;

    @GetMapping("/institutions/{id}/posts")
    public List<PostResponseDTO> listAll(@PathVariable int id) {

        return postService.listAll(id);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable int id) {
        return postService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
