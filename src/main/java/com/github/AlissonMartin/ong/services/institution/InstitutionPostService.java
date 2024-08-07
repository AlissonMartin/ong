package com.github.AlissonMartin.ong.services.institution;

import com.github.AlissonMartin.ong.dtos.PostCreateRequestDTO;
import com.github.AlissonMartin.ong.models.Post;
import com.github.AlissonMartin.ong.repositories.PostRepository;
import com.github.AlissonMartin.ong.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionPostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    S3Service s3Service;

    public Post create(PostCreateRequestDTO data) {
        Post post = new Post();

        post.setTitle(data.title());
        post.setBody(data.body());

        if (data.image() != null) {
            String imageUrl = s3Service.uploadFile(data.image());
            post.setImageUrl(imageUrl);
        }

        return postRepository.save(post);
    }
}
