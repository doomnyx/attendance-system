package pl.drat.dominik.dao.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomSubjectUserRepository {
    void deleteBySubjectId(Long subjectId);
    void deleteBySubjectIdAndUserID(Long subjectId, Long userId);
}
