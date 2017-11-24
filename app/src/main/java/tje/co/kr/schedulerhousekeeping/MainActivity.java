package tje.co.kr.schedulerhousekeeping;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity {

    private com.github.sundeepk.compactcalendarview.CompactCalendarView compactcalendarview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Event ev1 = new Event(Color.GREEN, 1433701251000L, "Some extra data that I want to store.");

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        compactcalendarview.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                CompactCalendarTab cct;
                List<Event> events = compactcalendarview.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });
    }

    @Override
    public void setValues() {
        compactcalendarview.setFirstDayOfWeek(Calendar.MONDAY);
    }

    @Override
    public void bindViews() {
        this.compactcalendarview = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

    }
}
