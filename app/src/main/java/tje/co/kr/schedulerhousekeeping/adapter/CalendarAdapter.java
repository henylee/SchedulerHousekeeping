package tje.co.kr.schedulerhousekeeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.List;

import tje.co.kr.schedulerhousekeeping.R;
import tje.co.kr.schedulerhousekeeping.data.Scheduler;

/**
 * Created by asqaw on 2017-11-28.
 */

public class CalendarAdapter extends ArrayAdapter<Scheduler> {

    Context mContext;
    List<Scheduler> mList;
    LayoutInflater inf;

    public CalendarAdapter(Context context, List<Scheduler> list) {

    }

}
