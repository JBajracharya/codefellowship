package com.jbajracharya.codefellowship.model;

import org.springframework.data.jpa.repository.JpaRepository;

//connects models to database
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    public ApplicationUser findByUserName(String userName);
}
