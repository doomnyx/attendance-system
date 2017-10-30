package pl.drat.dominik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.drat.dominik.dao.entity.Subject;
import pl.drat.dominik.dao.repository.SchedulePresenceWindowRepository;
import pl.drat.dominik.dto.request.StudentPresenceReportDTO;
import pl.drat.dominik.dao.entity.Presence;
import pl.drat.dominik.dao.entity.PresenceWindow;
import pl.drat.dominik.dao.entity.Schedule;
import pl.drat.dominik.dao.repository.PresenceRepository;
import pl.drat.dominik.dao.repository.ScheduleRepository;
import pl.drat.dominik.dto.PresenceWindowMessageDTO;
import pl.drat.dominik.dto.request.SchedulePresenceWindowDTO;
import pl.drat.dominik.util.DateUtil;
import pl.drat.dominik.util.PresenceCodeUtil;
import pl.drat.dominik.util.status.PresenceReportStatus;

import java.text.ParseException;
import java.util.*;

@Service
public class PresenceServiceImpl implements PresenceService {
    static final long ONE_MINUTE_IN_MILLIS = 60000;

    @Autowired
    PresenceRepository repository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    SchedulePresenceWindowRepository presenceWindowRepository;

    @Override
    public Presence createPresence(Presence presence) {
        return repository.save(presence);
    }

    @Override
    public Presence getPresence(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Presence updatePresence(Long id, Presence presence) {
        Presence existingPresence = repository.findOne(id);
        existingPresence.setPresenceStatus(presence.getPresenceStatus());
        return repository.save(existingPresence);
    }

    @Override
    public void deletePresence(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Presence> getPresenceList() {
        return (List<Presence>) repository.findAll();
    }

    @Override
    public List<Presence> getPresenceBySubjectId(Long subjectId) {
        return repository.findBySubjectId(subjectId);
    }

    @Override
    public List<Presence> getPresenceByStudentIdAndSubjectID(Long studentId, Long subjectId) {
        return repository.findByStudentIdAndSubjectId(studentId, subjectId);
    }

    @Override
    public PresenceWindowMessageDTO openSchedulePresenceWindow(SchedulePresenceWindowDTO presenceWindow) throws ParseException {

        List<PresenceWindow> presenceWindows = presenceWindowRepository.findByScheduleId(presenceWindow.getScheduleId());
        if (!presenceWindows.isEmpty()) {
            for (PresenceWindow window : presenceWindows) {
                if (DateUtil.nowIsBetween(window.getStartDate(), window.getEndDate())) {
                    String date = scheduleRepository.findById(presenceWindow.getScheduleId()).get(0).getDate();
                    presenceWindow.getScheduleId();
                    return new PresenceWindowMessageDTO("Presence window is open!", window.getSecurityCode(),date);
                }
            }
        }


        Long securityCode = PresenceCodeUtil.generatePresenceCode();
        PresenceWindow entity = new PresenceWindow();
        entity.setSecurityCode(securityCode);
        entity.setScheduleId(presenceWindow.getScheduleId());
        entity.setScheduleId(presenceWindow.getScheduleId());

        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date startDate = new Date(t);
        Date endDate = new Date(t + (presenceWindow.getShift() * ONE_MINUTE_IN_MILLIS));

        entity.setStartDate(DateUtil.toDBStringFormat(startDate));
        entity.setEndDate(DateUtil.toDBStringFormat(endDate));
        PresenceWindow persistedEntity = presenceWindowRepository.save(entity);

        String scheduleDate = scheduleRepository.findById(presenceWindow.getScheduleId()).get(0).getDate();
        presenceWindow.getScheduleId();
        return new PresenceWindowMessageDTO("New presence Window has been opened!", persistedEntity.getSecurityCode(),scheduleDate);
    }

    @Override
    public PresenceReportStatus reportPresenceByStudent(StudentPresenceReportDTO studentReport) throws ParseException {
        Schedule schedule = scheduleRepository.findOne(studentReport.getScheduleId());
        Subject subject = schedule.getSubject();
        List<Presence> presence = repository.findByStudentIdAndSubjectIdAndDateAndPresenceStatus(studentReport.getStudentId(), subject.getId(), schedule.getDate(), 1L);
        if (!presence.isEmpty()) {
            return PresenceReportStatus.PRESENCE_ALREADY_REPORTED;
        }
        Date now = new Date();
        String todayStr = DateUtil.to_yyyy_MM_dd(now);
        List<PresenceWindow> presenceWindows = presenceWindowRepository.findByScheduleIdAndStartDateContains(
                studentReport.getScheduleId(),
                todayStr);

        List<PresenceWindow> openedPresenceWindows = new ArrayList<>();
        for (PresenceWindow presenceWindow : presenceWindows) {
            if (DateUtil.nowIsBetween(presenceWindow.getStartDate(), presenceWindow.getEndDate())) {
                openedPresenceWindows.add(presenceWindow);
            }
        }

        for (PresenceWindow openedWindow : openedPresenceWindows) {
            if (openedWindow.getSecurityCode().equals(studentReport.getPresenceCode())) {


                Presence studentPresence = new Presence(studentReport.getStudentId(), subject.getId(), schedule.getDate(), 1L);
                repository.save(studentPresence);
                return PresenceReportStatus.ADDED_NEW_PRESENCE;
            }
        }

        if (openedPresenceWindows.size() > 0) return PresenceReportStatus.INCORRECT_PRESENCE_CODE;

        return PresenceReportStatus.CLOSED_PRESENCE_WINDOW;
    }

    @Override
    public void expirePresenceWindowForSubject(Long subjectId) throws ParseException {
        List<Schedule> subjectSchedules = scheduleRepository.findBySubjectId(subjectId);
        for(Schedule schedule:subjectSchedules){
            List<PresenceWindow> presenceWindows = presenceWindowRepository.findByScheduleId(schedule.getId());
            for (PresenceWindow window:presenceWindows){
                if(DateUtil.nowIsBetween(window.getStartDate(),window.getEndDate())){
                    window.setEndDate(DateUtil.nowDbFormat());
                    presenceWindowRepository.save(window);
                }
            }
        }
    }

    public Schedule getScheduleByScheduleId(Long scheduleId) throws ParseException {
        List<Schedule> schedules = scheduleRepository.findById(scheduleId);
            if(schedules.size()>0){
                return schedules.get(0);
            }
        return null;
    }


    @Override
    public PresenceWindow getActivePresenceWindow(Long scheduleId) throws ParseException {
        List<Schedule> subjectSchedules = scheduleRepository.findBySubjectId(scheduleId);
        for(Schedule schedule:subjectSchedules){
            List<PresenceWindow> presenceWindows = presenceWindowRepository.findByScheduleId(schedule.getId());
            for (PresenceWindow window:presenceWindows){
                if(DateUtil.nowIsBetween(window.getStartDate(),window.getEndDate())){
                    return window;
                }
            }
        }
        return null;
    }
}

