package pl.drat.dominik.dao.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomSubjectUserRepositoryImpl implements CustomSubjectUserRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public void deleteBySubjectId(Long subjectId) {


        em.createQuery("delete from SubjectUser S where S.subjectId = :subjectId")
                .setParameter("subjectId", subjectId)
                .executeUpdate();
    }

    @Override
    public void deleteBySubjectIdAndUserID(Long subjectId, Long userId) {
        em.createQuery("delete from SubjectUser S where S.subjectId = :subjectId and  S.userId = :userId")
                .setParameter("subjectId", subjectId)
                .setParameter("userId", userId)
                .executeUpdate();
    }
}
