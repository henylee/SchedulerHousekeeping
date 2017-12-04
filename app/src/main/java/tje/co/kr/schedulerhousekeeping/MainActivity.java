package tje.co.kr.schedulerhousekeeping;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
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
import com.gordonwong.materialsheetfab.DimOverlayFrameLayout;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import tje.co.kr.schedulerhousekeeping.adapter.CalendarAdapter;
import tje.co.kr.schedulerhousekeeping.adapter.PayMentAdapter;
import tje.co.kr.schedulerhousekeeping.data.Payment;
import tje.co.kr.schedulerhousekeeping.data.Scheduler;
import tje.co.kr.schedulerhousekeeping.util.ContextUtil;
import tje.co.kr.schedulerhousekeeping.util.GlobalData;

public class MainActivity extends BaseActivity {

    public static MainActivity act;

    BroadcastReceiver mBroad = new MySMSReceiver();
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;
    private com.applandeo.materialcalendarview.CalendarView calendarView;
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
    CalendarAdapter mCalendar;
    private ListView todayPayList;
    public PayMentAdapter mPayAdapter;
    private Fab fab;
    private com.gordonwong.materialsheetfab.DimOverlayFrameLayout overlay;
    private LinearLayout addDayLayout;
    private android.support.v7.widget.CardView fabsheet;
    private LinearLayout paymentLayout;
    private LinearLayout myProfileLayout;

    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    private TextView emptyListTxt;
    private LinearLayout scheduleEmptyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomActionBar();

        act = this;
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mBroad, intentFilter);

        TedPermission.with(this).setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS).check();

        bindViews();
        setupEvents();
        setValues();
    }

    public void refreshPayList() {
        mPayAdapter.notifyDataSetChanged();
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            if (ContextUtil.isFirstOk(mContext)) {
                Toast.makeText(mContext, "권한 허가", Toast.LENGTH_SHORT).show();
                ContextUtil.setIsFirstOk(mContext, false);
            }
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            ContextUtil.setIsFirstOk(mContext, false);
            Toast.makeText(mContext, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroad);
    }

    @Override
    public void setupEvents() {

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SignUpActivity.class);
                startActivity(intent);
                dlactivitymaindrawer.closeDrawer(llll);
            }
        });

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

        Fab fab = (Fab) findViewById(R.id.fab);
        View sheetView = findViewById(R.id.fab_sheet);
        View overlay = findViewById(R.id.overlay);
        int sheetColor = getResources().getColor(R.color.fab_sheet_color);
        int fabColor = getResources().getColor(R.color.fab_color);

        // Initialize material sheet FAB
        MaterialSheetFab materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay,
                sheetColor, fabColor);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                previewNote(eventDay);
            }
        });

        llll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent Event) {
                if (Event.getAction() == MotionEvent.ACTION_DOWN) {
                    return true;
                } else if (Event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        addDayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        paymentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PayMentActivity.class);
                startActivity(intent);
            }
        });

        myProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
            calendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            mCalendar.notifyDataSetChanged();
            calendarView.setEvents(mEventDays);
        }
    }

    private void addNote() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE);
    }

    private void previewNote(EventDay eventDay) {
        Intent intent = new Intent(this, NotePreviewActivity.class);
        intent.putExtra(EVENT, eventDay.getCalendar());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPayAdapter.notifyDataSetChanged();
    }

    @Override
    public void setValues() {
        mCalendar = new CalendarAdapter(mContext, GlobalData.mSchedul);
        todaySchedulList.setAdapter(mCalendar);
        todaySchedulList.setEmptyView(scheduleEmptyLayout);
        mPayAdapter = new PayMentAdapter(mContext, GlobalData.mPay);
        todayPayList.setAdapter(mPayAdapter);
        emptyListTxt.setTextColor(Color.parseColor("#ffffff"));

    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(act, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void bindViews() {
        this.dlactivitymaindrawer = (DrawerLayout) findViewById(R.id.dl_activity_main_drawer);
        this.llll = (LinearLayout) findViewById(R.id.llll);
        this.afterLoginLayout = (LinearLayout) findViewById(R.id.afterLoginLayout);
        this.serviceLayout2 = (LinearLayout) findViewById(R.id.serviceLayout2);
        this.todayPayList = (ListView) findViewById(R.id.todayPayList);
        this.scheduleEmptyLayout = (LinearLayout) findViewById(R.id.scheduleEmptyLayout);
        this.emptyListTxt = (TextView) findViewById(R.id.emptyListTxt);
        this.todaySchedulList = (ListView) findViewById(R.id.todaySchedulList);
        this.beforeLoginLayout = (LinearLayout) findViewById(R.id.beforeLoginLayout);
        this.signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        this.loginBtn = (TextView) findViewById(R.id.loginBtn);
        this.autoLoginCBox = (CheckBox) findViewById(R.id.autoLoginCBox);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
        this.myProfileImg = (ImageView) findViewById(R.id.myProfileImg);
        this.fabsheet = (CardView) findViewById(R.id.fab_sheet);
        this.myProfileLayout = (LinearLayout) findViewById(R.id.myProfileLayout);
        this.paymentLayout = (LinearLayout) findViewById(R.id.paymentLayout);
        this.addDayLayout = (LinearLayout) findViewById(R.id.addDayLayout);
        this.overlay = (DimOverlayFrameLayout) findViewById(R.id.overlay);
        this.fab = (Fab) findViewById(R.id.fab);
        this.calendarView = (CalendarView) findViewById(R.id.calendarView);
    }
}
