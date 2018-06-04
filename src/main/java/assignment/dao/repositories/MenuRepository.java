package assignment.dao.repositories;

import assignment.dao.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kek5 on 5/10/18.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
