package com.jbajracharya.codefellowship.model;


import javafx.geometry.Pos;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;



    @ManyToOne
    ApplicationUser applicationUser;

    public ApplicationUser getPostedBy() {
        return postedBy;
    }

    @ManyToOne
    ApplicationUser postedBy;

    String body;
    String createdAt;

    public long getId() {
        return id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Post(ApplicationUser applicationUser, String body, ApplicationUser postedBy) {
        this.applicationUser = applicationUser;
        this.body = body;
        this.postedBy = postedBy;
        this.createdAt = timestamp();
    }

    public Post(){

    }

    String timestamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
