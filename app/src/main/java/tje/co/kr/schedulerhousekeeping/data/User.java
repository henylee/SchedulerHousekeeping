package tje.co.kr.schedulerhousekeeping.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by joeun on 2017-11-24.
 */

public class User implements Serializable {

    private int id;
    private String userId;
    private String password;
    private String name;
    private String phone;

    public static User getUserFromJson(JSONObject jsonObject) {
        User u = new User();

        try {
            u.id = jsonObject.getInt("id");
            u.userId = jsonObject.getString("userId");
            u.password = jsonObject.getString("password");
            u.name = jsonObject.getString("name");
            u.phone = jsonObject.getString("phoneNum");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }

    public User() {

    }

    public User(int id, String userId, String password, String name, String phone) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
