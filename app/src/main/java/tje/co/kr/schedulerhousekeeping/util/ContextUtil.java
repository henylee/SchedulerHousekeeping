package tje.co.kr.schedulerhousekeeping.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by joeun on 2017-10-17.
 */

public class ContextUtil {

    private static final String prefName = "PermissionPref";

    private static final String IS_FIRST_OK = "IS_FIRST_OK";

    public static boolean isFirstOk (Context context) {
        SharedPreferences  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getBoolean(IS_FIRST_OK, true);
    }


    public static void setIsFirstOk (Context context, boolean isOK) {
        SharedPreferences  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(IS_FIRST_OK, isOK).commit();
    }


}
