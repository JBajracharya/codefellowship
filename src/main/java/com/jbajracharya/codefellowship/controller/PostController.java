package com.jbajracharya.codefellowship.controller;

import com.jbajracharya.codefellowship.model.ApplicationUser;
import com.jbajracharya.codefellowship.model.ApplicationUserRepository;
import com.jbajracharya.codefellowship.model.Post;
import com.jbajracharya.codefellowship.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class PostController {

    //make connection to databases
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/userDetails")
    public RedirectView makeAPost(long id, String body, Principal p){
        //get the post owner who is currently logged in
        ApplicationUser postOwner = applicationUserRepository.findById(id).get();
        ApplicationUser postedBy = applicationUserRepository.findByUserName(p.getName());
        System.out.println("postOwner = " + postOwner.getId());


        //save a post assigning post to the owner postowner
        Post postAssign = new Post(postOwner, body, postedBy);
        postRepository.save(postAssign);

        return new RedirectView("/users/" + id);
    }
}
