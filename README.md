## CodeFellowship Connect Application

### Steps to run the application
```
1. Clone repo on the local machine.
2. Type './gradlew BootRun' in terminal to start the program
3. Type localhost:8080/<routes> in the web browser to navigate to the different pages
```

### Routes:
```
* /                : Displays the application home pages which asks user to either log in or sign up 
* /sign-up         : Displays the form to register to be a member
* /log-in          : Displays the form to log in with username and password
* /profile         : Displays the logged in user profile page with profile picture and posts from other users to logged
                      in users
* /followingUsers  : Displays the list of users that are followed by the logged in user
* /users/{id}      : Displays the users and their feed
* /allUsers        : Displays all the members of codefellowship connect application
```

### Functionalities:
```
* Uses postgres database to store song data
* Allow users to sign up and be the member of the application. Spring security is used to create
  secure log in   
* Users can write post to other members of the application as well as follow each other
* User can view all the members of the application and click on their names to navigate to their page
```

### Tools and programming languages used:
```
 Spring framework, Spring Security, Spring DevTools, thymeleaf, java, html, css, postgres, gradle 
```