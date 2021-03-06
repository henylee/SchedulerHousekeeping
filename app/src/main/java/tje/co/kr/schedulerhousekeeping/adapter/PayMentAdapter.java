package tje.co.kr.schedulerhousekeeping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import tje.co.kr.schedulerhousekeeping.MySMSReceiver;
import tje.co.kr.schedulerhousekeeping.R;
import tje.co.kr.schedulerhousekeeping.data.Payment;

/**
 * Created by asqaw on 2017-11-28.
 */

public class PayMentAdapter extends ArrayAdapter<Payment> {

    Context mContext;
    List<Payment> mList;
    LayoutInflater inf;

    public PayMentAdapter(Context context, List<Payment> list) {
        super(context, R.layout.payment_list_item, list);

        mContext=context;
        mList=list;
        inf=LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;

        if (row==null) {
            row=inf.inflate(R.layout.payment_list_item, null);
        }

        Payment data = mList.get(position);

        TextView storeNameTxt = (TextView) row.findViewById(R.id.storeNameTxt);
        TextView timeTxt = (TextView) row.findViewById(R.id.timeTxt);
        TextView costTxt = (TextView) row.findViewById(R.id.costTxt);

        storeNameTxt.setText(data.getStoreName());
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Greenwich"));
        timeTxt.setText(sdf.format(data.getDateTime().getTime()));
        String temp = String.format(Locale.KOREA, "%,d원", data.getCost());
        costTxt.setText(temp);

        return row;
    }

}
