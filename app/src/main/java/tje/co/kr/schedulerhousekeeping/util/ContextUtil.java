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
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PASSWORD = "USER_PASSWORD";
    private static final String AUTO_LOGIN = "AUTO_LOGIN";


    public static boolean isFirstOk (Context context) {
        SharedPreferences  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getBoolean(IS_FIRST_OK, true);
    }


    public static void setIsFirstOk (Context context, boolean isOK) {
        SharedPreferences  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(IS_FIRST_OK, isOK).commit();
    }

    public static void setUserId(Context context, String userId) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_ID, userId).commit();

    }

    public static String getUserId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_ID, "아이디가없습니다");
    }

    public static void setAutoLogin(Context context, boolean login) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putBoolean(AUTO_LOGIN, login).commit();

    }

    public static boolean isAutoLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getBoolean(AUTO_LOGIN, false);
    }


    public static void setLoginUser(Context context, String name, String pw) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_NAME, name).commit();
        pref.edit().putString(USER_PASSWORD, pw).commit();

        loginUser = new User();

    }

    public static User getLoginUser(Context context) {

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (loginUser != null) {
            loginUser.setName(pref.getString(USER_NAME, ""));
            loginUser.setPassword(pref.getString(USER_PASSWORD, ""));
        }

        return loginUser;

    }

    public static void logout(Context context) {
        loginUser = null;

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_NAME, "").commit();
        pref.edit().putString(USER_PASSWORD,"").commit();

    }

}
