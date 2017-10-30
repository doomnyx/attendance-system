package pl.drat.dominik.service;

import pl.drat.dominik.dao.entity.User;

import java.util.List;

/**
 * Created by Dominik on 10.02.2017.
 */

public interface UserService {
    User createUser(User user);

    User getUser(Long id);
    User getByEmail(String username);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    List<User> getUserList();

    List<User> getAllStudents();

    List<User> getAllTeachers();

    List<User> findStudentsByFirstNameLastName(String firstName, String lastName);
}
