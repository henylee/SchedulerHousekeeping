package tje.co.kr.schedulerhousekeeping.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by joeun on 2017-10-17.
 */

public class ContextUtil {

    private static final String prefName = "PermissionPref";

    private static final boolean isFirOk = false;

    public static void isFirstOk (Context context) {
        SharedPreferences  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(isFirOk,false).commit();
    }

}
