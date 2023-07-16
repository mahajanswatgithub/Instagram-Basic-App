package com.example.InstagramBasic.Instagram.Basic.Design.repository;

import com.example.InstagramBasic.Instagram.Basic.Design.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post,Integer> {
}
