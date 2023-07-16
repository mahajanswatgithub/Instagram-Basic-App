package com.example.InstagramBasic.Instagram.Basic.Design.service;

import com.example.InstagramBasic.Instagram.Basic.Design.model.AuthenticationToken;
import com.example.InstagramBasic.Instagram.Basic.Design.model.Post;
import com.example.InstagramBasic.Instagram.Basic.Design.model.User;
import com.example.InstagramBasic.Instagram.Basic.Design.model.dto.SignInInput;
import com.example.InstagramBasic.Instagram.Basic.Design.model.dto.SignUpOutput;
import com.example.InstagramBasic.Instagram.Basic.Design.repository.IUserRepo;
import com.example.InstagramBasic.Instagram.Basic.Design.service.emailUtility.EmailHandler;
import com.example.InstagramBasic.Instagram.Basic.Design.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;
    public SignUpOutput signUpInstaUser(User user) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null){
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null){
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try{
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
            //saveAppointment the user with the new encrypted password

            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e){
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

    }

    public String signInInstaUser(SignInInput signInInput) {

        String signInStatusMessage = null;
        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(signInEmail);

        if(existingUser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(authToken);

                EmailHandler.sendEmail("mahajanswati922@gmail.com","email testing",authToken.getToken());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }

    public String createInstaPost(Post post,String email) {

        User postOwner = userRepo.findFirstByUserEmail(email);
        post.setPostOwner(postOwner);

        return postService.createInstaPost(post);
    }

    public String updateInstaUserByUserPhoneNumber(Integer id, String phoneNumber) {
         userRepo.updateInstaUserByUserPhoneNumber(id,phoneNumber);
         return "User Phone Number Updated Successfully";
    }
}
