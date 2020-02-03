package com.jbajracharya.codefellowship.controller;

import com.jbajracharya.codefellowship.model.ApplicationUser;
import com.jbajracharya.codefellowship.model.ApplicationUserRepository;
import com.jbajracharya.codefellowship.model.Post;
import com.jbajracharya.codefellowship.model.PostRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.jws.WebParam;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class ApplicationUserController {

    //connect model to database
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String userName, String password, String firstName, String lastName, String date, String bio) {
        // make the user AND salt and hash the password
        // this does the salting and hashing for you
        ApplicationUser newUser = new ApplicationUser(userName, passwordEncoder.encode(password), firstName, lastName, date, bio);
        // save user to db
        applicationUserRepository.save(newUser);

        // maybe autologin?
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/profile");
    }

    @GetMapping ("/log-in")
    public String showLogIn() {
        return "login";
    }

    @GetMapping("/profile")
    public String showProfile(Principal p, Model m) {
        if(p != null) {
            ApplicationUser loggedUser = applicationUserRepository.findByUserName(p.getName());

            m.addAttribute("loggedUser", loggedUser);
            m.addAttribute("loggedInUserName", p.getName());
        }
        return "profile";
    }

    @GetMapping("/users/{id}")
    public String showUserDetails(@PathVariable long id, Principal p, Model m) {
        ApplicationUser user = applicationUserRepository.findById(id).get();
        m.addAttribute("Principal", p.getName());
        m.addAttribute("loggedInUserName", p.getName());
        m.addAttribute("visitingUser", user.getUsername());
        m.addAttribute("userIdWeAreVisiting", user.getId());
        m.addAttribute("userWeAreVisiting", user);

        return "userDetails";
    }

    @PostMapping("/followMe")
//    parameter name has to match with the name in the form
    public RedirectView follow(long toBeFollowedId, Principal p) {
        ApplicationUser follower = applicationUserRepository.findByUserName(p.getName());
        ApplicationUser myId = applicationUserRepository.getOne(follower.getId());
        ApplicationUser toBeFollowed = applicationUserRepository.getOne(toBeFollowedId);
        myId.usersIAmFollowing.add(toBeFollowed);
//        toBeFollowed.usersThatAreFollowingMe.add(follower);
        applicationUserRepository.save(toBeFollowed);
        return new RedirectView("/users/" + toBeFollowedId);

    }

    @GetMapping("/followingUsers")
    public String usersIamFollowing(Principal p, Model m) {
        ApplicationUser follower = applicationUserRepository.findByUserName((p.getName()));
        m.addAttribute("loggedInUserName", follower.getUsername());
        m.addAttribute("usersFollowing", follower.usersIAmFollowing);
        for (ApplicationUser user: follower.usersIAmFollowing) {
            System.out.println("user.getUserName() = " + user.getUserName());
        }
        return "following";
    }

    @GetMapping("/allUsers")
    public String allUsers(Principal p, Model m) {
        List<ApplicationUser> allUsers = applicationUserRepository.findAll();
        m.addAttribute("loggedInUserName", p.getName());
        m.addAttribute("allUsers", allUsers);
        return "allMembers";
    }

}
