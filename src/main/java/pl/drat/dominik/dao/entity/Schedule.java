package pl.drat.dominik.dao.entity;

import pl.drat.dominik.dto.response.ScheduleDTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dominik on 24.07.2017.
 */
@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "date")
    private String date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Subject subject;

    public Schedule() {
    }

    public Schedule(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (id != null ? !id.equals(schedule.id) : schedule.id != null) return false;
        if (date != null ? !date.equals(schedule.date) : schedule.date != null) return false;
        return subject != null ? subject.equals(schedule.subject) : schedule.subject == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScheduleDTO toDto(){
        ScheduleDTO scheduleDto= new ScheduleDTO();
        scheduleDto.setId(getId());
        scheduleDto.setDate(getDate());
        return scheduleDto;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", date='" + date + '\'' +
                '}';
    }
}
