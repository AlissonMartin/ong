package com.github.AlissonMartin.ong.controllers.institution;

import com.github.AlissonMartin.ong.dtos.PostCreateRequestDTO;
import com.github.AlissonMartin.ong.dtos.PostResponseDTO;
import com.github.AlissonMartin.ong.models.Post;
import com.github.AlissonMartin.ong.services.institution.InstitutionPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/institution/posts")
public class InstitutionPostsController {

    @Autowired
    InstitutionPostService institutionPostService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> create(@RequestBody PostCreateRequestDTO body) {
        PostResponseDTO post = institutionPostService.create(body);

        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAll() {
        List<PostResponseDTO> posts = institutionPostService.findAll();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getById(@PathVariable int id) {
        PostResponseDTO post = institutionPostService.findById(id);
        if (post == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> update(@PathVariable int id, @RequestBody PostCreateRequestDTO body) {
        PostResponseDTO post = institutionPostService.update(id, body);
        if (post == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean deleted = institutionPostService.delete(id);
        if (!deleted) return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
