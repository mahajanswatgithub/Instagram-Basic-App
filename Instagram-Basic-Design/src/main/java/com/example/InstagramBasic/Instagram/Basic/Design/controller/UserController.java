package com.example.InstagramBasic.Instagram.Basic.Design.controller;

import com.example.InstagramBasic.Instagram.Basic.Design.model.Post;
import com.example.InstagramBasic.Instagram.Basic.Design.model.User;
import com.example.InstagramBasic.Instagram.Basic.Design.model.dto.SignInInput;
import com.example.InstagramBasic.Instagram.Basic.Design.model.dto.SignUpOutput;
import com.example.InstagramBasic.Instagram.Basic.Design.service.AuthenticationService;
import com.example.InstagramBasic.Instagram.Basic.Design.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    //sign up
    @PostMapping("user/signup")
    public SignUpOutput signUpInstaUser(@RequestBody @Valid User user){
        return userService.signUpInstaUser(user);
    }

    //sign in
    @PostMapping("user/signIn")
    public String signInInstaUser(@RequestBody @Valid SignInInput signInInput){
        return userService.signInInstaUser(signInInput);
    }

    //update user details phoneNumber
    @PutMapping("user/{id}/{phoneNumber}")
    public void updateInstaUserByUserPhoneNumber(@PathVariable Integer id,@PathVariable String phoneNumber)
    {
        userService.updateInstaUserByUserPhoneNumber(id,phoneNumber);
    }
}
