package assignment.dao.models;

import assignment.services.VoteService;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by kek5 on 5/21/18.
 */
@Data
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private MyUser user;

    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @Column(name = "restaurant_voted")
    private Long restaurant_id;

    public Vote(){}
    public Vote(MyUser user) {
        this.setUser(user);
    }

    @PrePersist
    private void onCreate() {
        this.dateUpdated = LocalDate.now(VoteService.ZONE_ID);
    }
}
