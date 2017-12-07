package tje.co.kr.schedulerhousekeeping.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by asqaw on 2017-11-28.
 */

public class Payment implements Serializable {

    private String storeName;
    private int cost;
    private String content;
    private Calendar dateTime;
    private int user_id;

    public static Payment getPayFromJson(JSONObject jsonObject) {
        Payment p = new Payment();

        try {
            p.storeName = jsonObject.getString("storeName");
            p.cost = jsonObject.getInt("cost");
            p.content = jsonObject.getString("content");
            p.dateTime=Calendar.getInstance();
            p.dateTime.setTimeInMillis(jsonObject.getLong("date"));
            p.user_id = jsonObject.getInt("user_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return p;
    }

    public Payment() {

    }

    public Payment(String storeName, int cost, String content, Calendar dateTime, int user_id) {
        this.storeName = storeName;
        this.cost = cost;
        this.content = content;
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
