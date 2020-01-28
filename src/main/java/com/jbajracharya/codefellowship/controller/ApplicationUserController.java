package com.jbajracharya.codefellowship.controller;

import com.jbajracharya.codefellowship.model.ApplicationUser;
import com.jbajracharya.codefellowship.model.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
public class ApplicationUserController {

    //connect model to database
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String userName, String password, String firstName, String lastName, String date, String bio) {
        // make the user AND salt and hash the password
        // this does the salting and hashing for you
        ApplicationUser newUser = new ApplicationUser(userName, passwordEncoder.encode(password), firstName, lastName, date, bio);
        // save user to db
        applicationUserRepository.save(newUser);

        return new RedirectView("/");
    }
}
