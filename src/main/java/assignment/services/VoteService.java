package assignment.services;

import assignment.dao.models.MyUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;


public interface VoteService {
    LocalTime EXPIRATION_TIME = LocalTime.of(11, 0);
    ZoneId ZONE_ID = ZoneId.of("UTC");


    default MyUser getCurrentUser() {
        return (MyUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    void checkVotingAvailability(LocalTime localTime);
    void vote(Long rest_id, LocalDateTime localDateTime);

}
