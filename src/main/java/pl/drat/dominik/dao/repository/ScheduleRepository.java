package pl.drat.dominik.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.drat.dominik.dao.entity.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule,Long> {
    List<Schedule> findBySubjectId(Long subjectId);
    List<Schedule> findById(Long scheduleId);
}
