package assignment.web.errors.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalTime;

/**
 * Created by kek5 on 5/24/18.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VotingIsUnavailableException extends RuntimeException {
    @Getter
    @Setter
    private String message = "Voting is available after " +
            LocalTime.of(11,0).toString() + " and until " + LocalTime.of(0, 0).toString() + "\n" + "Your current time: ";

    public VotingIsUnavailableException(String time) {
        super();
        this.message += time;
    }


}
