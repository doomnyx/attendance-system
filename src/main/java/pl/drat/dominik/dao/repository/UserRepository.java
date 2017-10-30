package pl.drat.dominik.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.drat.dominik.dao.entity.User;

import java.util.List;

/**
 * Created by Dominik on 10.02.2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByRole(String role);
    List<User> findByFirstNameAndLastNameAndRole(String firstName,String lastName,String role);
}
