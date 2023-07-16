package com.example.InstagramBasic.Instagram.Basic.Design.repository;

import com.example.InstagramBasic.Instagram.Basic.Design.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String newEmail);

    @Modifying
    @Transactional
    @Query(value = "update Users set User_Phone_Number = :phoneNumber where User_Id = :id",nativeQuery = true)
    void updateInstaUserByUserPhoneNumber(Integer id, String phoneNumber);

}
