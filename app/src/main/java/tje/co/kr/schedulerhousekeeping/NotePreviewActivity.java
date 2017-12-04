package tje.co.kr.schedulerhousekeeping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import tje.co.kr.schedulerhousekeeping.adapter.CalendarAdapter;
import tje.co.kr.schedulerhousekeeping.adapter.PayMentAdapter;
import tje.co.kr.schedulerhousekeeping.util.GlobalData;

public class NotePreviewActivity extends BaseActivity {

    private android.widget.TextView note;
    private android.widget.ListView todaySchedulList;
    private TextView emptyListTxt;
    private android.widget.LinearLayout scheduleEmptyLayout;
    private android.widget.ListView todayPayList;
    CalendarAdapter mCalendar;
    PayMentAdapter mPay;
    private TextView costTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_preview);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        Intent intent = getIntent();
        Calendar tempCal = (Calendar) intent.getSerializableExtra(MainActivity.EVENT);

        String tempDay = getFormattedDate(tempCal.getTime());
        setTitle(tempDay);

    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    @Override
    public void setValues() {
        mCalendar = new CalendarAdapter(mContext, GlobalData.mSchedul);
        todaySchedulList.setAdapter(mCalendar);
        todaySchedulList.setEmptyView(scheduleEmptyLayout);
        mPay = new PayMentAdapter(mContext, GlobalData.mPay);
        todayPayList.setAdapter(mPay);
    }

    @Override
    public void bindViews() {
        this.costTxt = (TextView) findViewById(R.id.costTxt);
        this.todayPayList = (ListView) findViewById(R.id.todayPayList);
        this.scheduleEmptyLayout = (LinearLayout) findViewById(R.id.scheduleEmptyLayout);
        this.emptyListTxt = (TextView) findViewById(R.id.emptyListTxt);
        this.todaySchedulList = (ListView) findViewById(R.id.todaySchedulList);
    }
}
