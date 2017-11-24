package tje.co.kr.schedulerhousekeeping;

import android.os.Bundle;

import com.applandeo.materialcalendarview.CalendarView;

public class MainActivity extends BaseActivity {

    private com.applandeo.materialcalendarview.CalendarView calendarView;
    private android.support.design.widget.FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.floatingActionButton = (android.support.design.widget.FloatingActionButton) findViewById(R.id.floatingActionButton);
        this.calendarView = (CalendarView) findViewById(R.id.calendarView);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

    }
}
