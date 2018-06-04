package assignment.dao.repositories;

import assignment.dao.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kek5 on 5/20/18.
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
