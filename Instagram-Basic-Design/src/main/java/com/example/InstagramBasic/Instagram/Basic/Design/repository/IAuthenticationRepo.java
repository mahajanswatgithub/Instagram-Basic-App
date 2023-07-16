package com.example.InstagramBasic.Instagram.Basic.Design.repository;

import com.example.InstagramBasic.Instagram.Basic.Design.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findFirstByToken(String authTokenValue);
}
