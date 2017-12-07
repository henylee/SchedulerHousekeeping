package tje.co.kr.schedulerhousekeeping.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by asqaw on 2017-11-28.
 */

public class Payment implements Serializable {

    private String storeName;
    private int cost;
    private Calendar dateTime;
    private int user_id;

    public Payment() {

    }

    public Payment(String storeName, int cost, Calendar dateTime, int user_id) {
        this.storeName = storeName;
        this.cost = cost;
        this.dateTime = dateTime;
        this.user_id = user_id;
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
