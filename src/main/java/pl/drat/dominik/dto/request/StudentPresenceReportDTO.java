package pl.drat.dominik.dto.request;

import java.io.Serializable;

public class StudentPresenceReportDTO implements Serializable {
    Long studentId;
    Long scheduleId;
    Long presenceCode;
    String studentIpAddress;

    public StudentPresenceReportDTO() {
    }

    public StudentPresenceReportDTO(Long studentId, Long scheduleId, Long presenceCode, String studentIpAddress) {
        this.studentId = studentId;
        this.scheduleId = scheduleId;
        this.presenceCode = presenceCode;
        this.studentIpAddress = studentIpAddress;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getPresenceCode() {
        return presenceCode;
    }

    public void setPresenceCode(Long presenceCode) {
        this.presenceCode = presenceCode;
    }

    public String getStudentIpAddress() {
        return studentIpAddress;
    }

    public void setStudentIpAddress(String studentIpAddress) {
        this.studentIpAddress = studentIpAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentPresenceReportDTO that = (StudentPresenceReportDTO) o;

        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (scheduleId != null ? !scheduleId.equals(that.scheduleId) : that.scheduleId != null) return false;
        if (presenceCode != null ? !presenceCode.equals(that.presenceCode) : that.presenceCode != null) return false;
        return studentIpAddress != null ? studentIpAddress.equals(that.studentIpAddress) : that.studentIpAddress == null;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (scheduleId != null ? scheduleId.hashCode() : 0);
        result = 31 * result + (presenceCode != null ? presenceCode.hashCode() : 0);
        result = 31 * result + (studentIpAddress != null ? studentIpAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentPresenceReportDTO{" +
                "studentId=" + studentId +
                ", scheduleId=" + scheduleId +
                ", presenceCode=" + presenceCode +
                ", studentIpAddress='" + studentIpAddress + '\'' +
                '}';
    }
}
