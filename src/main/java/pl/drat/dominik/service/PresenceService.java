package pl.drat.dominik.service;

import pl.drat.dominik.dao.entity.PresenceWindow;
import pl.drat.dominik.dao.entity.Schedule;
import pl.drat.dominik.dto.request.StudentPresenceReportDTO;
import pl.drat.dominik.dao.entity.Presence;
import pl.drat.dominik.dto.PresenceWindowMessageDTO;
import pl.drat.dominik.dto.request.SchedulePresenceWindowDTO;
import pl.drat.dominik.util.status.PresenceReportStatus;

import java.text.ParseException;
import java.util.List;

public interface PresenceService {

    Presence createPresence(Presence presence);

    Presence getPresence(Long id);

    Presence updatePresence(Long id, Presence presence);

    void deletePresence(Long id);

    List<Presence> getPresenceList();

    List<Presence> getPresenceBySubjectId(Long subjectId);
    List<Presence> getPresenceByStudentIdAndSubjectID(Long studentId, Long subjectId);

    PresenceWindowMessageDTO openSchedulePresenceWindow(SchedulePresenceWindowDTO presenceWindow) throws ParseException;

    PresenceReportStatus reportPresenceByStudent(StudentPresenceReportDTO studentReport) throws ParseException;

    void expirePresenceWindowForSubject(Long subjectId) throws ParseException;

    PresenceWindow getActivePresenceWindow(Long scheduleId) throws ParseException;

     Schedule getScheduleByScheduleId(Long scheduleId) throws ParseException;
}
