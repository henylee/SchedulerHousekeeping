package tje.co.kr.schedulerhousekeeping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class MySMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        if (bundle!=null) {
            Object [] pdus = (Object[]) bundle.get("pdus");

            SmsMessage[] msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                String sender = msgs[i].getOriginatingAddress();
                String content = msgs[i].getMessageBody().toString();

                if (sender.equals("16449999")) {

                }
            }
        }

    }
}
