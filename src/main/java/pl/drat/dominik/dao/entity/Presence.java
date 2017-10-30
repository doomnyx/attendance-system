package pl.drat.dominik.dao.entity;

import pl.drat.dominik.dto.response.PresenceDTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dominik on 24.07.2017.
 */
@Entity
@Table(name = "presence")
public class Presence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private Long presenceStatus;

    public Presence() {
    }

    public Presence(Long studentId, Long subjectId, String date, Long status) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.date = date;
        this.presenceStatus = status;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getPresenceStatus() {
        return presenceStatus;
    }

    public void setPresenceStatus(Long presenceStatus) {
        this.presenceStatus = presenceStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Presence presence = (Presence) o;

        if (id != null ? !id.equals(presence.id) : presence.id != null) return false;
        if (!studentId.equals(presence.studentId)) return false;
        if (!subjectId.equals(presence.subjectId)) return false;
        if (!date.equals(presence.date)) return false;
        return presenceStatus.equals(presence.presenceStatus);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + studentId.hashCode();
        result = 31 * result + subjectId.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + presenceStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Presence{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", date='" + date + '\'' +
                ", presenceStatus=" + presenceStatus +
                '}';
    }

    public PresenceDTO toDTO(){
        PresenceDTO presenceDTO = new PresenceDTO();
        presenceDTO.setId(getId());
        presenceDTO.setStudentId(getStudentId());
        presenceDTO.setSubjectId(getSubjectId());
        presenceDTO.setPresenceStatus(getPresenceStatus());
        presenceDTO.setDate(getDate());

        return presenceDTO;
    }
}
