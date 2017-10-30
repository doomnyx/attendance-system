package pl.drat.dominik.dto.response;

import java.io.Serializable;

public class PresenceDTO implements Serializable {
    Long id;
    Long studentId;
    String date;
    Long presenceStatus;
    private Long subjectId;

    public PresenceDTO() {
    }

    public PresenceDTO(Long id, Long studentId, String date, Long presenceStatus, Long subjectId) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.presenceStatus = presenceStatus;
        this.subjectId = subjectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PresenceDTO that = (PresenceDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (presenceStatus != null ? !presenceStatus.equals(that.presenceStatus) : that.presenceStatus != null)
            return false;
        return subjectId != null ? subjectId.equals(that.subjectId) : that.subjectId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (presenceStatus != null ? presenceStatus.hashCode() : 0);
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PresenceDTO{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", date='" + date + '\'' +
                ", presenceStatus=" + presenceStatus +
                ", subjectId=" + subjectId +
                '}';
    }
}
