package pl.drat.dominik.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.drat.dominik.dao.entity.Subject;

/**
 * Created by Dominik on 24.07.2017.
 */
@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
