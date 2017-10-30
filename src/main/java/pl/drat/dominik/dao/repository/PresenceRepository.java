package pl.drat.dominik.dao.repository;

import org.springframework.data.repository.CrudRepository;
import pl.drat.dominik.dao.entity.Presence;

import java.util.List;

public interface PresenceRepository extends CrudRepository<Presence, Long> {
    List<Presence> findBySubjectId(Long subjectId);

    List<Presence> findByStudentIdAndSubjectId(Long studentId, Long subjectId);
    List<Presence> findByStudentIdAndSubjectIdAndDateAndPresenceStatus(Long studentId, Long subjectId, String date, Long presenceStatus);
}
