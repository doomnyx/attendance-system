package pl.drat.dominik.dto.response;

import java.io.Serializable;

public class ScheduleDTO implements Serializable {
    private String date;
    private Long id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

        ScheduleDTO that = (ScheduleDTO) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "date='" + date + '\'' +
                ", id=" + id +
                '}';
    }
}
