package pl.drat.dominik.dao.repository;

import org.springframework.stereotype.Repository;
import pl.drat.dominik.dto.response.ScheduleDTO;

@Repository
public interface CustomScheduleRepository {
    void deleteBySubjectId(Long subjectId);
    void deleteScheduleForSubject(Long subjectId, Long scheduleId);
    void createNewSchedule(Long subjectId, ScheduleDTO scheduleDTOToAdd);
}
