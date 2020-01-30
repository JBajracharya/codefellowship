package com.jbajracharya.codefellowship.model;

import javafx.geometry.Pos;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToMany
    @JoinTable(
            name = "following",
            joinColumns = { @JoinColumn(name = "followingOthers")},
            inverseJoinColumns = {@JoinColumn(name = "followingMe")}
    )
    Set<ApplicationUser> usersIAmFollowing;

    @ManyToMany(mappedBy = "usersIAmFollowing")
    Set<ApplicationUser> usersThatAreFollowingMe;

    public void followedUser(ApplicationUser usersFollowing) {
        usersIAmFollowing.add(usersFollowing);
    }

    @OneToMany(mappedBy = "applicationUser")
    List<Post> posts;

    public List<Post> getPosts(){
        return this.posts;
    }

    String userName;
    String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    String firstName;
    String lastName;
    String date;
    String bio;


    public ApplicationUser() {
    }

    public ApplicationUser(String userName, String password, String firstName, String lastName, String date, String bio) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.bio = bio;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
