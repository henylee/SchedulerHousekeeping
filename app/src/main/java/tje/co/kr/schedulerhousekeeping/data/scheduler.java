package tje.co.kr.schedulerhousekeeping.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by joeun on 2017-11-24.
 */

public class scheduler implements Serializable {

    private String title;
    private String location;
    private Date start_date;
    private Date end_date;
    private float lat;
    private float lng;

    public scheduler() {

    }

    public scheduler(String title, String location, Date start_date, Date end_date, float lat, float lng) {
        this.title = title;
        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
        this.lat = lat;
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
