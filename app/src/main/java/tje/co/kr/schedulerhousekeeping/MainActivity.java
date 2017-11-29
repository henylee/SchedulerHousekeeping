package tje.co.kr.schedulerhousekeeping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.List;

import tje.co.kr.schedulerhousekeeping.adapter.CalendarAdapter;
import tje.co.kr.schedulerhousekeeping.adapter.PayMentAdapter;
import tje.co.kr.schedulerhousekeeping.data.Payment;
import tje.co.kr.schedulerhousekeeping.data.Scheduler;

public class MainActivity extends BaseActivity {

    BroadcastReceiver mBroad = new MySMSReceiver();
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;
    private com.applandeo.materialcalendarview.CalendarView calendarView;
    private android.support.design.widget.FloatingActionButton floatingActionButton;
    private List<EventDay> mEventDays = new ArrayList<>();
    private android.widget.ImageView myProfileImg;
    private android.widget.EditText idEdt;
    private android.widget.EditText pwEdt;
    private android.widget.CheckBox autoLoginCBox;
    private android.widget.TextView loginBtn;
    private android.widget.TextView signUpBtn;
    private android.widget.LinearLayout beforeLoginLayout;
    private android.widget.LinearLayout afterLoginLayout;
    private android.widget.LinearLayout llll;
    private android.support.v4.widget.DrawerLayout dlactivitymaindrawer;
    private LinearLayout serviceLayout2;
    private android.widget.ListView todaySchedulList;
    List<Scheduler> mSchedul = new ArrayList<>();
    CalendarAdapter mCalendar;
    private ListView todayPayList;
    List<Payment> mPay=new ArrayList<>();
    PayMentAdapter mPayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomActionBar();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mBroad, intentFilter);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroad);
    }

    @Override
    public void setupEvents() {

        hamburgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlactivitymaindrawer.openDrawer(llll);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlactivitymaindrawer.closeDrawer(llll);
                beforeLoginLayout.setVisibility(View.GONE);
                afterLoginLayout.setVisibility(View.VISIBLE);
                Toast.makeText(mContext, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                previewNote(eventDay);
            }
        });

        llll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent Event) {
                if (Event.getAction()==MotionEvent.ACTION_DOWN) {
                    return true;
                }
                else if (Event.getAction()==MotionEvent.ACTION_MOVE) {
                    return true;
                }
                else {
                    return false;
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
            calendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            calendarView.setEvents(mEventDays);
        }
    }
    private void addNote() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE);
    }
    private void previewNote(EventDay eventDay) {
        Intent intent = new Intent(this, NotePreviewActivity.class);
        if(eventDay instanceof MyEventDay){
            intent.putExtra(EVENT, (MyEventDay) eventDay);
        }
        startActivity(intent);
    }

    @Override
    public void setValues() {
        mCalendar = new CalendarAdapter(mContext, mSchedul);
        todaySchedulList.setAdapter(mCalendar);
        mPayAdapter=new PayMentAdapter(mContext, mPay);
        todayPayList.setAdapter(mPayAdapter);
    }

    @Override
    public void bindViews() {
        this.dlactivitymaindrawer = (DrawerLayout) findViewById(R.id.dl_activity_main_drawer);
        this.llll = (LinearLayout) findViewById(R.id.llll);
        this.afterLoginLayout = (LinearLayout) findViewById(R.id.afterLoginLayout);
        this.serviceLayout2 = (LinearLayout) findViewById(R.id.serviceLayout2);
        this.todayPayList = (ListView) findViewById(R.id.todayPayList);
        this.todaySchedulList = (ListView) findViewById(R.id.todaySchedulList);
        this.beforeLoginLayout = (LinearLayout) findViewById(R.id.beforeLoginLayout);
        this.signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        this.loginBtn = (TextView) findViewById(R.id.loginBtn);
        this.autoLoginCBox = (CheckBox) findViewById(R.id.autoLoginCBox);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
        this.myProfileImg = (ImageView) findViewById(R.id.myProfileImg);
        this.floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        this.calendarView = (CalendarView) findViewById(R.id.calendarView);
    }
}
