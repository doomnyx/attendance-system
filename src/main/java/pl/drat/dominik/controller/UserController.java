package pl.drat.dominik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.drat.dominik.dao.entity.Subject;
import pl.drat.dominik.dao.entity.User;
import pl.drat.dominik.dto.response.UserDTO;
import pl.drat.dominik.service.SubjectService;
import pl.drat.dominik.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dominik on 04.02.2017.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SubjectService subjectService;


    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user.toDTO(), HttpStatus.OK);
    }


    @GetMapping("/get/firstname/{firstName}/lastname/{lastName}")
    public ResponseEntity<List<UserDTO>> getStudentsByFirstNameLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        List<User> students = userService.findStudentsByFirstNameLastName(firstName,lastName);

        List<UserDTO> studentsDTO = new ArrayList<>();
        for (User student : students) {
            studentsDTO.add(student.toDTO());
        }
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/assignedToSubject/{subjectId}")
    public ResponseEntity<List<UserDTO>> getAssignedUsersToSubject(@PathVariable("subjectId") Long subjectId) {
        Subject subject = subjectService.getSubject(subjectId);
        Set<User> users = subject.getUsers();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(user.toDTO());
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/get/allStudents")
    public ResponseEntity<List<UserDTO>> getAllStudents() {
        List<User> users = userService.getAllStudents();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(user.toDTO());
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/get/allTeachers")
    public ResponseEntity<List<UserDTO>> getAllTeachers() {
        List<User> users = userService.getAllTeachers();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(user.toDTO());
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
}
