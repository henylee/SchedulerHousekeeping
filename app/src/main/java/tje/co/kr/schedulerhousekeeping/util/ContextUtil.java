package tje.co.kr.schedulerhousekeeping.util;

import android.content.Context;
import android.content.SharedPreferences;

import tje.co.kr.schedulerhousekeeping.data.User;

/**
 * Created by joeun on 2017-10-17.
 */

public class ContextUtil {

    private static User loginUser = null;

    private static final String prefName = "PermissionPref";

    private static final String IS_FIRST_OK = "IS_FIRST_OK";
    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PASSWORD = "USER_PASSWORD";
    private static final String USER_PHONE = "USER_PHONE";
    private static final String AUTO_LOGIN = "AUTO_LOGIN";


    public static boolean isFirstOk (Context context) {
        SharedPreferences  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getBoolean(IS_FIRST_OK, true);
    }


    public static void setIsFirstOk (Context context, boolean isOK) {
        SharedPreferences  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(IS_FIRST_OK, isOK).commit();
    }

    public static void setId(Context context, int id) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putInt(ID, id).commit();

    }

    public static int getId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getInt(ID, 0);
    }

    public static void setUserId(Context context, String userId) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_ID, userId).commit();

    }

    public static String getUserId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_ID, "아이디가없습니다");
    }

    public  static void  setUserPhone(Context context, String phone) {

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_PHONE, phone).commit();
    }

    public static String getUserPhone(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_PHONE, "");
    }

    public static void setUserName(Context context, String userName) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_NAME, userName).commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_NAME, "");

    }

    public static void setAutoLogin(Context context, boolean login) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putBoolean(AUTO_LOGIN, login).commit();

    }

    public static boolean getAutoLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getBoolean(AUTO_LOGIN, false);
    }

    public static void setLoginUser(Context context, int id, String userId, String pw, String name, String phoneNum) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putInt(ID, id).commit();
        pref.edit().putString(USER_NAME, name).commit();
        pref.edit().putString(USER_ID, userId).commit();
        pref.edit().putString(USER_PASSWORD, pw).commit();
        pref.edit().putString(USER_PHONE, phoneNum).commit();


    }

    public static User getLoginUser(Context context) {

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (pref.getString(USER_ID, "").equals("")) {
            loginUser = null;
        }
        else {
            loginUser = new User();
            loginUser.setId(pref.getInt(ID, 0));
            loginUser.setName(pref.getString(USER_NAME, ""));
            loginUser.setUserId(pref.getString(USER_ID, ""));
            loginUser.setPassword(pref.getString(USER_PASSWORD, ""));
            loginUser.setPhone(pref.getString(USER_PHONE, ""));
        }

        return loginUser;

    }

    public static void logout(Context context) {
        loginUser = null;

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putInt(ID, 0).commit();
        pref.edit().putString(USER_ID, "").commit();
        pref.edit().putString(USER_PASSWORD, "").commit();
        pref.edit().putString(USER_NAME, "").commit();
        pref.edit().putString(USER_PHONE,"").commit();

    }

}
