package assignment.dao.repositories;

import assignment.dao.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kek5 on 4/30/18.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
