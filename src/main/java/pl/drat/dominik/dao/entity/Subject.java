package pl.drat.dominik.dao.entity;

import pl.drat.dominik.dto.response.ScheduleDTO;
import pl.drat.dominik.dto.response.SubjectDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dominik on 24.07.2017.
 */
@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private String year;

    @Column(name = "semester")
    private int semester;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "subject")
    private Set<Schedule> schedules;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "subjects")
    private Set<User> users;

    public Subject() {
    }

    public Subject(String name, String year, int semester,Set<Schedule> schedules) {
        if(schedules == null) this.schedules = new HashSet<>();
        else this.schedules = schedules;
        this.name = name;
        this.year = year;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;

    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (semester != subject.semester) return false;
        if (id != null ? !id.equals(subject.id) : subject.id != null) return false;
        if (!name.equals(subject.name)) return false;
        return year.equals(subject.year);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + semester;
        return result;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", semester=" + semester +
                '}';
    }

    public SubjectDTO toDto(){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(getId());
        subjectDTO.setName(getName());
        subjectDTO.setSemester(getSemester());
        subjectDTO.setYear(getYear());
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for(Schedule schedule:getSchedules()){
            scheduleDTOs.add(schedule.toDto());
        }
        subjectDTO.setSchedules(scheduleDTOs);
        return subjectDTO;
    }
}
