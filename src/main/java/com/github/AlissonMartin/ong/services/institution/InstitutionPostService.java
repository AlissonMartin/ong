package com.github.AlissonMartin.ong.services.institution;

import com.github.AlissonMartin.ong.dtos.PostCreateRequestDTO;
import com.github.AlissonMartin.ong.dtos.PostResponseDTO;
import com.github.AlissonMartin.ong.models.Post;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.PostRepository;
import com.github.AlissonMartin.ong.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class InstitutionPostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    S3Service s3Service;

    public PostResponseDTO create(PostCreateRequestDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Post post = new Post();

        post.setTitle(data.title());
        post.setBody(data.body());
        post.setInstitution(user.getInstitution());

        if (data.image() != null) {
            String imageUrl = s3Service.uploadFile(data.image());
            post.setImageUrl(imageUrl);
        }

        Post saved = postRepository.save(post);
        return toDTO(saved);
    }

    public PostResponseDTO findById(int id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) return null;
        return toDTO(post);
    }

    public java.util.List<PostResponseDTO> findAll() {
        return postRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PostResponseDTO update(int id, PostCreateRequestDTO data) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) return null;
        post.setTitle(data.title());
        post.setBody(data.body());
        if (data.image() != null) {
            String imageUrl = s3Service.uploadFile(data.image());
            post.setImageUrl(imageUrl);
        }
        Post saved = postRepository.save(post);
        return toDTO(saved);
    }

    public boolean delete(int id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) return false;
        postRepository.delete(post);
        return true;
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
