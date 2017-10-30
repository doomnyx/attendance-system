package pl.drat.dominik.dao.repository;

import org.springframework.stereotype.Repository;
import pl.drat.dominik.dto.response.ScheduleDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomScheduleRepositoryImpl implements CustomScheduleRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public void deleteBySubjectId(Long subjectId) {
        em.createQuery("delete from Schedule S where S.subject.id = :subjectId")
                .setParameter("subjectId", subjectId)
                .executeUpdate();
    }

    @Override
    public void deleteScheduleForSubject(Long subjectId, Long scheduleId) {
        em.createQuery("delete from Schedule S where S.subject.id = :subjectId and S.id = :scheduleId")
                .setParameter("subjectId", subjectId)
                .setParameter("scheduleId", scheduleId)
                .executeUpdate();
    }

    public void createNewSchedule(Long subjectId, ScheduleDTO scheduleDTOToAdd){
        String query = "INSERT INTO Schedule VALUES(?,?)";
        em.createNativeQuery(query)
                .setParameter(1, subjectId)
                .setParameter(2, scheduleDTOToAdd.getDate())
                .executeUpdate();
    }

}
