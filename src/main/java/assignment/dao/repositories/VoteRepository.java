package assignment.dao.repositories;

import assignment.dao.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kek5 on 5/30/18.
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
