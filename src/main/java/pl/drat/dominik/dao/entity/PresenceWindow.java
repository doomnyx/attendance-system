package pl.drat.dominik.dao.entity;


import pl.drat.dominik.dto.request.SchedulePresenceWindowDTO;

import javax.persistence.*;

@Entity
@Table(name = "schedule_presence_window")
public class PresenceWindow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "security_code")
    private Long securityCode;

    public PresenceWindow() {
    }

    public PresenceWindow(Long scheduleId, String startDate, String endDate, Long securityCode) {
        this.scheduleId = scheduleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.securityCode = securityCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Long securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PresenceWindow that = (PresenceWindow) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (scheduleId != null ? !scheduleId.equals(that.scheduleId) : that.scheduleId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        return securityCode != null ? securityCode.equals(that.securityCode) : that.securityCode == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (scheduleId != null ? scheduleId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (securityCode != null ? securityCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PresenceWindow{" +
                "id=" + id +
                ", scheduleId=" + scheduleId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", securityCode=" + securityCode +
                '}';
    }

    public SchedulePresenceWindowDTO toDto(){
        SchedulePresenceWindowDTO window = new SchedulePresenceWindowDTO();
        window.setId(getId());
        window.setScheduleId(getScheduleId());
        return window;
    }
}
