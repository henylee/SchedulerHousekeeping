package tje.co.kr.schedulerhousekeeping.FCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by asqaw on 2017-12-07.
 */

public class MyFCMIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d("토큰값", FirebaseInstanceId.getInstance().getToken());
    }
}
