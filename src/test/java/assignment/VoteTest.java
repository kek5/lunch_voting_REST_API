package assignment;

import assignment.config.ApiConfig;
import assignment.config.SecurityConfig;
import assignment.config.SpringConfiguration;
import assignment.dao.models.MyUser;
import assignment.dao.models.Role;
import assignment.dao.repositories.VoteRepository;
import assignment.services.VoteService;
import assignment.services.impl.SignUpService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by kek5 on 5/31/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({ApiConfig.class, SecurityConfig.class, SpringConfiguration.class})
public class VoteTest {
    @Autowired
    private VoteService voteService;

//    @Autowired
//    private AuthenticationManagerBuilder auth;
//
//    @Autowired
//    private VoteRepository voteRepository;
//
//    @Autowired
//    private SignUpService signUpService;
/*
    @org.junit.Test
    @WithMockUser(username = "user", password = "user")
    public void test() throws Exception {
        voteService.vote(1L, LocalDateTime.of(LocalDate.of(2018, 12, 12), LocalTime.of(12,0)));
    }
*/
}
