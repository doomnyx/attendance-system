package pl.drat.dominik.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.drat.dominik.dao.entity.SubjectUser;
import pl.drat.dominik.dao.entity.SubjectUserPK;

@Repository
public interface SubjectUserRepository extends CrudRepository<SubjectUser, SubjectUserPK> {
}
