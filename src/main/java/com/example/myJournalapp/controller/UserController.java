//package com.example.myJournalapp.controller;
//
//import com.example.myJournalapp.entity.User;
//import com.example.myJournalapp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PutMapping
//    public ResponseEntity<?>  updateUser(@RequestBody User user){
//       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName  =  authentication.getName();
//        User userInDb = userService.findByUserName(userName);
//        userInDb.setUserName(user.getUserName());
//        userInDb.setPassword(user.getPassword());
//        userService.saveEntry(userInDb);
//        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//@DeleteMapping
//public ResponseEntity<?> deleteUserById (){
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    userRepository.deleteByUserName(authentication.getName());
//    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//}
//}



package com.example.myJournalapp.controller;

import com.example.myJournalapp.entity.User;
import com.example.myJournalapp.repository.UserRepository;
import com.example.myJournalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Find the user in the database
        User userInDb = userService.findByUserName(userName);

        if (userInDb == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update the user's username
        userInDb.setUserName(user.getUserName());

        // If a new password is provided, hash it and update the password
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            userInDb.setPassword(hashedPassword);
        }

        // Save the updated user back to the database
        userService.saveNewEntry(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Delete the user by their username
        userRepository.deleteByUserName(userName);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
