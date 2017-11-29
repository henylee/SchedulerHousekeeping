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

    public Payment() {

    }

    public Payment(String storeName, int cost, Calendar dateTime) {
        this.storeName = storeName;
        this.cost = cost;
        this.dateTime = dateTime;
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
}
