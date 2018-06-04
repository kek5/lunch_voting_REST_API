package assignment.services.impl;

import assignment.dao.models.MyUser;
import assignment.dao.models.Vote;
import assignment.dao.repositories.UserRepository;
import assignment.dao.repositories.VoteRepository;
import assignment.services.VoteService;
import assignment.web.errors.exceptions.VotingIsUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by kek5 on 5/24/18.
 */

@Service
@Slf4j
public class VoteServiceImpl implements VoteService {
    private VoteRepository voteRepository;
    private UserRepository userRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void checkVotingAvailability(LocalTime currentTime) {
        if (currentTime.isAfter(VoteService.EXPIRATION_TIME)) {
            throw new VotingIsUnavailableException(currentTime.toString());
        }
    }


    @Override
    public void vote(Long rest_id, LocalDateTime localDateTime) {
        this.checkVotingAvailability(LocalTime.now(VoteService.ZONE_ID));
        MyUser user  = userRepository.findByUsername(getCurrentUser().getUsername()).get();

        if (hasAlreadyVotedToday(user.getVote())) {
            updateVote(rest_id, user);
        } else {
            createVote(rest_id, user);
        }

    }

    private boolean hasAlreadyVotedToday(Vote vote) {
        return vote.getDateUpdated().isEqual(LocalDate.now(VoteService.ZONE_ID));
    }

    private void updateVote(Long rest_id, MyUser user) {
        Vote vote = user.getVote();
        vote.setRestaurant_id(rest_id);
        voteRepository.save(vote);

    }
    private void createVote(Long rest_id, MyUser user) {
        Vote vote = new Vote(user);
        vote.setRestaurant_id(rest_id);
        vote.setDateUpdated(LocalDate.now(VoteService.ZONE_ID));

        user.setVote(vote);
        userRepository.save(user);
    }
}
