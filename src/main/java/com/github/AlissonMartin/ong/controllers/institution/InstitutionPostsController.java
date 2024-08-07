package com.github.AlissonMartin.ong.controllers.institution;

import com.github.AlissonMartin.ong.dtos.PostCreateRequestDTO;
import com.github.AlissonMartin.ong.models.Post;
import com.github.AlissonMartin.ong.services.institution.InstitutionPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/institution/posts")
public class InstitutionPostsController {

    @Autowired
    InstitutionPostService institutionPostService;

    @PostMapping
    public ResponseEntity create(@RequestBody PostCreateRequestDTO body) {
        Post post = institutionPostService.create(body);

        return ResponseEntity.ok(post);
    }
}
