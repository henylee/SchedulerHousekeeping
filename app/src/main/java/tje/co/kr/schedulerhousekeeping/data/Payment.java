package tje.co.kr.schedulerhousekeeping.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asqaw on 2017-11-28.
 */

public class Payment implements Serializable {

    private String storeName;
    private String pay;
    private int cost;
    private Date day;

    public Payment() {

    }

    public Payment(String storeName, String pay, int cost, Date day) {
        this.storeName = storeName;
        this.pay = pay;
        this.cost = cost;
        this.day = day;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
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
}
