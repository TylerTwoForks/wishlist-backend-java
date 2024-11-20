package com.edmondsinc.wishlist.model;

import com.edmondsinc.wishlist.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserJpaTest {

    @Autowired
    UserRepo userRepo;

    @Test
    public void testCreateUser(){
        User user = new User.Builder()
                .setUserName("testCase@tyler.com")
                .setFirstName("Tyler")
                .setLastName("Edmonds")
                .build();
        userRepo.save(user);
        List<User> users = userRepo.findAll();

        users.forEach(u -> System.out.println("user name:: "+u.userName));
        users.forEach(System.out::println);


//        Assertions.assertThat(users).extracting(User::getUserName).contains("testCase@tyler.com");
//        Assertions.assertThat(users.size()).isEqualTo(1);

//        userRepo.deleteAll();
//        Assertions.assertThat(userRepo.findAll()).isEmpty();
    }

    @Test
    public void tesDeleteAllUsers(){
        userRepo.deleteAll();
        Assertions.assertThat(userRepo.findAll()).isEmpty();
    }
}
