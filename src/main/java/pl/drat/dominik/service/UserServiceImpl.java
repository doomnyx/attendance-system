package pl.drat.dominik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.drat.dominik.dao.repository.UserRepository;
import pl.drat.dominik.dao.entity.User;

import java.util.List;

/**
 * Created by Dominik on 10.02.2017.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return repository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
    return repository.findByEmail(email);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = repository.findOne(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setRole(user.getRole());
        return repository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        repository.delete(id);

    }

    @Override
    public List<User> getUserList() {
        List<User> userList = (List<User>) repository.findAll();
        return userList;
    }

    @Override
    public List<User> getAllStudents() {
        List<User> students = repository.findByRole("student");
        return  students;
    }

    @Override
    public List<User> getAllTeachers() {
        List<User> teachers = repository.findByRole("teacher");
        return  teachers;
    }

    @Override
    public List<User> findStudentsByFirstNameLastName(String firstName, String lastName) {
        List<User> students = repository.findByFirstNameAndLastNameAndRole(firstName,lastName,"student");
        return students;
    }
}
