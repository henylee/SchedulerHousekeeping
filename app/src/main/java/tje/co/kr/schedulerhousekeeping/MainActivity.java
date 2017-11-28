package tje.co.kr.schedulerhousekeeping;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.rd.PageIndicatorView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

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
    private android.widget.TextView cashTxt;
    private android.widget.ImageView closeBtn2;
    private android.widget.LinearLayout noTicketLayout;
    private android.support.v4.view.ViewPager ticketViewPager;
    private com.rd.PageIndicatorView pageIndicatorView;
    private android.widget.LinearLayout userTicketLayout;
    private android.widget.TextView phoneNumTxt;
    private android.widget.TextView emailTxt;
    private android.widget.ImageView imageView;
    private android.widget.TextView carNumTxt;
    private android.widget.ExpandableListView expandMenuListView;
    private android.widget.LinearLayout serviceLayout2;
    private android.widget.LinearLayout afterLoginLayout;
    private android.widget.LinearLayout llll;
    private android.support.v4.widget.DrawerLayout dlactivitymaindrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomActionBar();

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        hamburgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlactivitymaindrawer.openDrawer(llll);
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

    }

    @Override
    public void bindViews() {
        this.dlactivitymaindrawer = (DrawerLayout) findViewById(R.id.dl_activity_main_drawer);
        this.llll = (LinearLayout) findViewById(R.id.llll);
        this.afterLoginLayout = (LinearLayout) findViewById(R.id.afterLoginLayout);
        this.serviceLayout2 = (LinearLayout) findViewById(R.id.serviceLayout2);
        this.expandMenuListView = (ExpandableListView) findViewById(R.id.expandMenuListView);
        this.carNumTxt = (TextView) findViewById(R.id.carNumTxt);
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.emailTxt = (TextView) findViewById(R.id.emailTxt);
        this.phoneNumTxt = (TextView) findViewById(R.id.phoneNumTxt);
        this.userTicketLayout = (LinearLayout) findViewById(R.id.userTicketLayout);
        this.pageIndicatorView = (com.rd.PageIndicatorView) findViewById(R.id.pageIndicatorView);
        this.ticketViewPager = (ViewPager) findViewById(R.id.ticketViewPager);
        this.noTicketLayout = (LinearLayout) findViewById(R.id.noTicketLayout);
        this.closeBtn2 = (ImageView) findViewById(R.id.close_Btn2);
        this.cashTxt = (TextView) findViewById(R.id.cashTxt);
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
