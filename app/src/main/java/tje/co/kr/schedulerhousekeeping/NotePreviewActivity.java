package tje.co.kr.schedulerhousekeeping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tje.co.kr.schedulerhousekeeping.adapter.TodayAdapter;
import tje.co.kr.schedulerhousekeeping.adapter.TodayPayAdapter;
import tje.co.kr.schedulerhousekeeping.data.Payment;
import tje.co.kr.schedulerhousekeeping.data.Scheduler;
import tje.co.kr.schedulerhousekeeping.util.GlobalData;

public class NotePreviewActivity extends BaseActivity {

    private android.widget.TextView note;
    private android.widget.ListView todaySchedulList;
    private TextView emptyListTxt;
    private android.widget.LinearLayout scheduleEmptyLayout;
    private android.widget.ListView todayPayList;
    TodayAdapter mCalendar;
    TodayPayAdapter mPay;
    List<Payment> showPayList = new ArrayList<>();
    List<Scheduler> showSchedulList = new ArrayList<>();
    private TextView costTxt;

    Calendar selectedDay = null;
    int todaySum = 0;
    private android.widget.Button addNoteBtn;

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
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tempintent = new Intent(mContext, AddNoteActivity.class);
                tempintent.putExtra("선택된일자", selectedDay);
                startActivity(tempintent);
            }
        });

    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    @Override
    public void setValues() {

        Intent intent = getIntent();
        selectedDay = (Calendar) intent.getSerializableExtra(MainActivity.EVENT);

        String tempDay = getFormattedDate(selectedDay.getTime());
        setTitle(tempDay);

        mCalendar = new TodayAdapter(mContext, showSchedulList);
        todaySchedulList.setAdapter(mCalendar);
        todaySchedulList.setEmptyView(scheduleEmptyLayout);
        mPay = new TodayPayAdapter(mContext, showPayList);
        todayPayList.setAdapter(mPay);

        calSumOfToday();
        todayList();

    }

    private void todayList() {
        showSchedulList.clear();

        for (Scheduler s : GlobalData.mSchedul) {
            if (s.getDateTime().get(Calendar.YEAR) == selectedDay.get(Calendar.YEAR) && s.getDateTime().get(Calendar.MONTH) == selectedDay.get(Calendar.MONTH) && s.getDateTime().get(Calendar.DAY_OF_MONTH) == selectedDay.get(Calendar.DAY_OF_MONTH)) {

                showSchedulList.add(s);
            }
        }
        mCalendar.notifyDataSetChanged();
    }

    private void calSumOfToday() {

        todaySum = 0;
        showPayList.clear();
        for(Payment p : GlobalData.mPay) {
            if (p.getDateTime().get(Calendar.YEAR) == selectedDay.get(Calendar.YEAR) && p.getDateTime().get(Calendar.MONTH) == selectedDay.get(Calendar.MONTH) && p.getDateTime().get(Calendar.DAY_OF_MONTH) == selectedDay.get(Calendar.DAY_OF_MONTH)) {

                showPayList.add(p);
                todaySum += p.getCost();
            }
        }

        mPay.notifyDataSetChanged();

        costTxt.setText(String.format(Locale.KOREA, "%,d원", todaySum));

    }

    @Override
    public void bindViews() {
        this.costTxt = (TextView) findViewById(R.id.costTxt);
        this.todayPayList = (ListView) findViewById(R.id.todayPayList);
        this.scheduleEmptyLayout = (LinearLayout) findViewById(R.id.scheduleEmptyLayout);
        this.emptyListTxt = (TextView) findViewById(R.id.emptyListTxt);
        this.todaySchedulList = (ListView) findViewById(R.id.todaySchedulList);
        this.addNoteBtn = (Button) findViewById(R.id.addNoteBtn);
    }
}
