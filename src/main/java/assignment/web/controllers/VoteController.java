package assignment.web.controllers;

import assignment.config.ApiConfig;
import assignment.services.impl.RestaurantService;
import assignment.services.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * Created by kek5 on 5/30/18.
 */
@RestController
@RequestMapping(ApiConfig.API_ENTRY + ApiConfig.VERSION + "/vote")
@Slf4j
public class VoteController {
    private VoteService voteService;
    private RestaurantService restaurantService;

    @Autowired
    public VoteController(VoteService voteService, RestaurantService restaurantService) {
        this.voteService = voteService;
        this.restaurantService = restaurantService;
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> vote(@RequestParam(value = "rest_id") Long rest_id) {
        restaurantService.checkIfExist(rest_id);
        voteService.vote(rest_id, LocalDateTime.now(VoteService.ZONE_ID));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
