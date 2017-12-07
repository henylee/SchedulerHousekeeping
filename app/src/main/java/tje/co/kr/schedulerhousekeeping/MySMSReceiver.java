package tje.co.kr.schedulerhousekeeping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tje.co.kr.schedulerhousekeeping.adapter.PayMentAdapter;
import tje.co.kr.schedulerhousekeeping.data.Payment;
import tje.co.kr.schedulerhousekeeping.util.ContextUtil;
import tje.co.kr.schedulerhousekeeping.util.GlobalData;

public class MySMSReceiver extends BroadcastReceiver {

    PayMentAdapter mAdapter;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("myreceiver", intent.getAction());

        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Log.d("onReceive()", "문자가 수신되었습니다.");
        }

        Bundle bundle = intent.getExtras();

        if (bundle!=null) {
            Object pdus[] = (Object[]) bundle.get("pdus");
            SmsMessage msgs[] = new SmsMessage[pdus.length];

            for (int i=0; i<msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                String sender = msgs[i].getOriginatingAddress();
                String content = msgs[i].getMessageBody().toString();
                Log.d("send", sender);
                Log.d("content", content);

                if (sender.equals("01093453926")) {
                    String[] message = content.split("\n");

                    String store = message[3];
                    String costTxt = message[5];
                    String tempTxt= costTxt.replace(",","");
                    int cost = Integer.parseInt(tempTxt);
                    String dateStr = message[1].split(" ")[0];
                    String timeStr = message[1].split(" ")[1];
                    String parse = dateStr+" "+timeStr;

                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
                    Date date=new Date();
                    try {
                        date=sdf.parse(parse);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Calendar temp = Calendar.getInstance();
                    temp.setTime(date);

                    GlobalData.mPay.add(new Payment(store, cost,temp, ContextUtil.getId(context)));

                    MainActivity.act.refreshPayList();

                }
            }
        }

    }
}
