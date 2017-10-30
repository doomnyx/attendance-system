package pl.drat.dominik.service;

import pl.drat.dominik.dao.entity.Subject;
import pl.drat.dominik.dao.entity.SubjectUser;
import pl.drat.dominik.dto.response.ScheduleDTO;
import pl.drat.dominik.dto.response.SubjectDTO;

import java.util.List;

public interface SubjectService {

    Subject createSubject(Subject subject);

    Subject getSubject(Long id);

    Subject updateSubject(Long id, SubjectDTO subject);

    void deleteSubject(Long id);

    List<Subject> getSubjectList();

    List<Subject>  getSubjectsAssignedToUser(Long userId);

    void deleteSchedule(Long subjectId, Long scheduleId);

    void deleteUser(Long subjectId, Long studentId);

    Subject createSchedule(Long subjectId, ScheduleDTO scheduleDTO);

    void assignSubjectToTeacher(Subject newSubject, Long teacherId);

    SubjectUser assignStudentToSubject(Long studentId, Long subjectId);
}
