package pl.drat.dominik.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.drat.dominik.dao.entity.Role;

/**
 * Created by Dominik on 24.07.2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
