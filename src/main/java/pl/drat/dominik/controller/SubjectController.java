package pl.drat.dominik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.drat.dominik.dao.entity.Subject;
import pl.drat.dominik.dao.entity.SubjectUser;
import pl.drat.dominik.dao.entity.User;
import pl.drat.dominik.dto.response.ScheduleDTO;
import pl.drat.dominik.dto.response.SubjectDTO;
import pl.drat.dominik.service.SubjectService;
import pl.drat.dominik.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    UserService userService;


    @GetMapping("/get/{id}")
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable("id") Long id) {
        Subject subject = subjectService.getSubject(id);
        return new ResponseEntity<>(subject.toDto(), HttpStatus.OK);
    }

    @PostMapping("/create/teacher/{teacherId}")
    public ResponseEntity<Subject> createSubjectForTeacher(@RequestBody Subject subject, @PathVariable("teacherId") Long teacherId) {
        Subject newSubject = subjectService.createSubject(subject);
        subjectService.assignSubjectToTeacher(newSubject, teacherId);
        return new ResponseEntity<>(newSubject, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO, @PathVariable("id") Long id) {
        Subject updatedSubject = subjectService.updateSubject(id, subjectDTO);
        return new ResponseEntity<>(updatedSubject.toDto(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable("id") Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/assignedToUser/{userId}")
    public ResponseEntity<List<SubjectDTO>> getAssignedSubjectsToUser(@PathVariable("userId") Long userId) {
        User user = userService.getUser(userId);
        Set<Subject> subjects = user.getSubjects();

        List<SubjectDTO> subjectDTOs = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectDTOs.add(subject.toDto());
        }
        return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
    }

    @DeleteMapping("{subjectId}/schedule/{scheduleId}")
    public ResponseEntity<Subject> deleteScheduleForSubject(@PathVariable("subjectId") Long subjectId, @PathVariable("scheduleId") Long scheduleId) {
        subjectService.deleteSchedule(subjectId, scheduleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{subjectId}/student/{studentId}")
    public ResponseEntity<Subject> deleteUserForSubject(@PathVariable("subjectId") Long subjectId, @PathVariable("studentId") Long studentId) {
        subjectService.deleteUser(subjectId, studentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/create/{subjectId}/schedule")
    public ResponseEntity<SubjectDTO> createNewScheduleForSubject(@RequestBody ScheduleDTO scheduleDTO, @PathVariable("subjectId") Long subjectId) {
        Subject newSubject = subjectService.createSchedule(subjectId, scheduleDTO);
        return new ResponseEntity<>(newSubject.toDto(), HttpStatus.OK);
    }

    @GetMapping("/assignstudent/{studentId}/tosubject/{subjectId}")
    public ResponseEntity<SubjectUser> getAssignedSubjectsToUser(@PathVariable("subjectId") Long subjectId, @PathVariable("studentId") Long studentId) {
        SubjectUser subjectUser = subjectService.assignStudentToSubject(studentId,subjectId);

        return new ResponseEntity<>(subjectUser,HttpStatus.OK);
    }
}
