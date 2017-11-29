package tje.co.kr.schedulerhousekeeping.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Timer;

/**
 * Created by asqaw on 2017-11-28.
 */

public class Payment implements Serializable {

    private String storeName;
    private int cost;
    private Date day;
    private Timer time;

    public Payment() {

    }

    public Payment(String storeName, int cost, Date day, Timer time) {
        this.storeName = storeName;
        this.cost = cost;
        this.day = day;
        this.time = time;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Timer getTime() {
        return time;
    }

    public void setTime(Timer time) {
        this.time = time;
    }
}
