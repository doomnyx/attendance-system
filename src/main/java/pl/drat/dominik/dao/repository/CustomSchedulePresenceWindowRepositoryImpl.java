package pl.drat.dominik.dao.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomSchedulePresenceWindowRepositoryImpl implements CustomSchedulePresenceWindowRepository {

    @PersistenceContext
    EntityManager em;


}
