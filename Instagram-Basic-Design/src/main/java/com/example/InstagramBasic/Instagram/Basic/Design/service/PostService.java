package com.example.InstagramBasic.Instagram.Basic.Design.service;

import com.example.InstagramBasic.Instagram.Basic.Design.model.Post;
import com.example.InstagramBasic.Instagram.Basic.Design.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    public String createInstaPost(Post post) {

        post.setPostCreatedDate(new Timestamp(System.currentTimeMillis()));
        postRepo.save(post);
        return "Post uploaded!!!!";
    }

    public Iterable<Post> getAllPosts() {
        return postRepo.findAll();
    }
}
