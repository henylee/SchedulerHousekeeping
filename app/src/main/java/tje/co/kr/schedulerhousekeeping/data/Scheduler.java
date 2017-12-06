package tje.co.kr.schedulerhousekeeping.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by joeun on 2017-11-24.
 */

public class Scheduler implements Serializable {

    private String title;
    private String content;
    private Calendar dateTime;
    private int user_id;

    public Scheduler() {

    }

    public Scheduler(String title, String content, Calendar dateTime, int user_id) {
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
