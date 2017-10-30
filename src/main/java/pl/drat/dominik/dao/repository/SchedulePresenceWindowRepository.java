package pl.drat.dominik.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.drat.dominik.dao.entity.PresenceWindow;

import java.util.List;

@Repository
public interface SchedulePresenceWindowRepository extends CrudRepository<PresenceWindow,Long>{

    List<PresenceWindow> findByScheduleIdAndStartDateContains(long scheduleId, String startDate);

    List<PresenceWindow> findByScheduleId(Long scheduleId);
}
