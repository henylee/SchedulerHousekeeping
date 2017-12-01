package tje.co.kr.schedulerhousekeeping.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by joeun on 2017-11-24.
 */

public class Scheduler implements Serializable {

    private String content;
    private Calendar dateTime;

    public Scheduler() {

    }

    public Scheduler(String content, Calendar dateTime) {
        this.content = content;
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }
}
