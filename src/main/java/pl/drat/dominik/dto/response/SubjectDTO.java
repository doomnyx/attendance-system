package pl.drat.dominik.dto.response;

import java.io.Serializable;
import java.util.List;

public class SubjectDTO implements Serializable {

    private Long id;

    private String name;

    private String year;

    private int semester;
    private List<ScheduleDTO> schedules;

    public SubjectDTO() {
    }

    public SubjectDTO(Long id, String name, String year, int semester, List<ScheduleDTO> schedules, List<UserDTO> users) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.schedules = schedules;
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

    public List<ScheduleDTO> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleDTO> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectDTO that = (SubjectDTO) o;

        if (semester != that.semester) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        return schedules != null ? schedules.equals(that.schedules) : that.schedules == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + semester;
        result = 31 * result + (schedules != null ? schedules.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubjectDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", semester=" + semester +
                ", schedules=" + schedules +
                '}';
    }
}
