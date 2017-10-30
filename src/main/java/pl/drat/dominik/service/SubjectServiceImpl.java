package pl.drat.dominik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.drat.dominik.dao.entity.Schedule;
import pl.drat.dominik.dao.entity.Subject;
import pl.drat.dominik.dao.entity.SubjectUser;
import pl.drat.dominik.dao.repository.*;
import pl.drat.dominik.dto.response.ScheduleDTO;
import pl.drat.dominik.dto.response.SubjectDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CustomScheduleRepository customScheduleRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SubjectUserRepository subjectUserRepository;
    @Autowired
    private CustomSubjectUserRepository customSubjectUserRepository;


    @Override
    public Subject createSubject(Subject subject) {
        Set<Schedule> newSchedules = subject.getSchedules();
        if (newSchedules != null) {
            for (Schedule schedule : newSchedules) {
                schedule.setSubject(subject);
            }
        }
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubject(Long id) {
        return subjectRepository.findOne(id);
    }

    @Override
    public Subject updateSubject(Long id, SubjectDTO subject) {


        Subject existingSubject = subjectRepository.findOne(id);
        existingSubject.setName(subject.getName());
        existingSubject.setSemester(subject.getSemester());
        existingSubject.setYear(subject.getYear());

        return existingSubject;
    }

    @Override
    public void deleteSubject(Long subjectId) {
        customScheduleRepository.deleteBySubjectId(subjectId);
        customSubjectUserRepository.deleteBySubjectId(subjectId);
        subjectRepository.delete(subjectId);
    }

    @Override
    public List<Subject> getSubjectList() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public List<Subject> getSubjectsAssignedToUser(Long userId) {
        return null;
    }

    @Override
    public void deleteSchedule(Long subjectId, Long scheduleId) {
        customScheduleRepository.deleteScheduleForSubject(subjectId, scheduleId);
    }

    @Override
    public void deleteUser(Long subjectId, Long studentId) {
        customSubjectUserRepository.deleteBySubjectIdAndUserID(subjectId, studentId);
    }

    @Override
    public Subject createSchedule(Long subjectId, ScheduleDTO scheduleDTO) {
        Schedule newSchedule = new Schedule();
        newSchedule.setSubject(subjectRepository.findOne(subjectId));
        newSchedule.setDate(scheduleDTO.getDate());
        scheduleRepository.save(newSchedule);
        return subjectRepository.findOne(subjectId);
    }

    @Override
    public void assignSubjectToTeacher(Subject newSubject, Long teacherId) {
        subjectUserRepository.save(new SubjectUser(newSubject.getId(), teacherId));

    }

    @Override
    public SubjectUser assignStudentToSubject(Long studentId, Long subjectId) {
        return subjectUserRepository.save(new SubjectUser(subjectId, studentId));
    }
}
