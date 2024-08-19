//package com.example.myJournalapp.service;
//
//import com.example.myJournalapp.entity.User;
//import com.example.myJournalapp.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//public class UserServiceTests{
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
//    @Test
//    @ArgumentsSource(UserArgumentsProvider.class)
//    public void testFindByUserName(User user){
//        assertTrue(userService.saveNewEntry(user));
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2, 10, 12",
//            "3,3,9"
//    })
//    public void test(int a , int b , int expected){
//        assertEquals(expected, a + b);
//    }
//}


package com.example.myJournalapp.service;

import com.example.myJournalapp.entity.User;
import com.example.myJournalapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests{

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        // You can initialize the userService and userRepository here if needed
    }

    @Test
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testFindByUserName(User user){
        assertNotNull(userService.saveNewEntry(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2, 10, 12",
            "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}