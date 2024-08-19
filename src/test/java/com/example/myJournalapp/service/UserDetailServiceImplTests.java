//package com.example.myJournalapp.service;
//
//import com.example.myJournalapp.entity.User;
//import com.example.myJournalapp.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.*;
//
//@ActiveProfiles("dev")
//public class UserDetailServiceImplTests {
//
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void loadUserByUsernameTests(){
//        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("nio").password("inrinrick").roles(new ArrayList<>()).build());
//       UserDetails user  =  userDetailsService.loadUserByUsername("nio");
//        Assertions.assertNotNull(user);
//    }
//}



package com.example.myJournalapp.service;

import com.example.myJournalapp.entity.User;
import com.example.myJournalapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ActiveProfiles("dev")
@SpringBootTest
public class UserDetailServiceImplTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTests(){
        when(userRepository.findByUserName(anyString())).thenReturn(User.builder().userName("nio").password("inrinrick").roles(new ArrayList<>()).build());
        UserDetails user  =  userDetailsService.loadUserByUsername("nio");
        Assertions.assertNotNull(user);
    }
}