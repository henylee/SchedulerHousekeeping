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
import java.util.List;

import tje.co.kr.schedulerhousekeeping.R;
import tje.co.kr.schedulerhousekeeping.data.Scheduler;

/**
 * Created by asqaw on 2017-12-04.
 */

public class TodayAdapter extends ArrayAdapter<Scheduler> {

    Context mContext;
    List<Scheduler> mList;
    LayoutInflater inf;

    public TodayAdapter(Context context, List<Scheduler> list) {
        super(context, R.layout.today_schedul_list, list);

        mContext=context;
        mList=list;
        inf=LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;

        if (row==null) {
            row=inf.inflate(R.layout.today_schedul_list, null);
        }

        Scheduler data = mList.get(position);

        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);
        TextView dateTxt = (TextView) row.findViewById(R.id.dateTxt);

        contentTxt.setText(data.getContent());
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        dateTxt.setText(sdf.format(data.getDateTime().getTime()));

        return row;
    }

}
