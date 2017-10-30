package pl.drat.dominik.dao.entity;

import java.io.Serializable;

public class SubjectUserPK implements Serializable {

    private Long subjectId;

    private Long userId;

    public SubjectUserPK() {
    }

    public SubjectUserPK(Long subjectId, Long userId) {
        this.subjectId = subjectId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectUserPK that = (SubjectUserPK) o;

        if (subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;
    }

    @Override
    public int hashCode() {
        int result = subjectId != null ? subjectId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubjectUserPK{" +
                "subjectId=" + subjectId +
                ", userId=" + userId +
                '}';
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
