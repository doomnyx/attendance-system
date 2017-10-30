package pl.drat.dominik.dto.request;

import java.io.Serializable;

public class SchedulePresenceWindowDTO  implements Serializable{
    private Long id;
    private Long scheduleId;
    private Long shift;

    public SchedulePresenceWindowDTO() {
    }

    public SchedulePresenceWindowDTO(Long id, Long scheduleId, Long shift) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.shift = shift;
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

    public Long getShift() {
        return shift;
    }

    public void setShift(Long shift) {
        this.shift = shift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchedulePresenceWindowDTO that = (SchedulePresenceWindowDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (scheduleId != null ? !scheduleId.equals(that.scheduleId) : that.scheduleId != null) return false;
        return shift != null ? shift.equals(that.shift) : that.shift == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (scheduleId != null ? scheduleId.hashCode() : 0);
        result = 31 * result + (shift != null ? shift.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SchedulePresenceWindowDTO{" +
                "id=" + id +
                ", scheduleId=" + scheduleId +
                ", shift=" + shift +
                '}';
    }
}
