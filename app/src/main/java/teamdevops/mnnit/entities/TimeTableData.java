package teamdevops.mnnit.entities;

/**
 * Created by king on 24-11-2015.
 */
public class TimeTableData {

    private String subject;
    private String code;
    private String venue;
    private String instructor;
    private int from_time;
    private int to_time;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getFrom_time() {
        return from_time;
    }

    public void setFrom_time(int from_time) {
        this.from_time = from_time;
    }

    public int getTo_time() {
        return to_time;
    }

    public void setTo_time(int to_time) {
        this.to_time = to_time;
    }
}

