package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.dtos.PostResponseDTO;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.models.Post;
import com.github.AlissonMartin.ong.repositories.InstitutionRepository;
import com.github.AlissonMartin.ong.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private InstitutionRepository institutionRepository;

    public List<PostResponseDTO> listAll(int id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Institution not found with id: " + id));
        return postRepository.findByInstitution(institution).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<PostResponseDTO> findById(int id) {
        return postRepository.findById(id).map(this::toDTO);
    }

    private PostResponseDTO toDTO(Post post) {
        return new PostResponseDTO(
            post.getId(),
            post.getTitle(),
            post.getBody(),
            post.getImageUrl(),
            post.getCreatedAt(),
            post.getUpdatedAt(),
            post.getInstitution() != null ? post.getInstitution().getId() : null
        );
    }
}
