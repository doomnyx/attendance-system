package pl.drat.dominik.dto;

public class PresenceWindowMessageDTO {
    String message;
    Long presenceCode;
    String date;


    public PresenceWindowMessageDTO(String message, Long presenceCode, String date) {
        this.message = message;
        this.presenceCode = presenceCode;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getPresenceCode() {
        return presenceCode;
    }

    public void setPresenceCode(Long presenceCode) {
        this.presenceCode = presenceCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PresenceWindowMessageDTO that = (PresenceWindowMessageDTO) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (presenceCode != null ? !presenceCode.equals(that.presenceCode) : that.presenceCode != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (presenceCode != null ? presenceCode.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}