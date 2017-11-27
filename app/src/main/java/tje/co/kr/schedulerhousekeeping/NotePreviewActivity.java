package tje.co.kr.schedulerhousekeeping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotePreviewActivity extends BaseActivity {

    private android.widget.TextView note;

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
        if (intent != null) {
            Object event = intent.getParcelableExtra(MainActivity.EVENT);
            if(event instanceof MyEventDay){
                MyEventDay myEventDay = (MyEventDay)event;
                getSupportActionBar().setTitle(getFormattedDate(myEventDay.getCalendar().getTime()));
                note.setText(myEventDay.getNote());
                return;
            }
            if(event instanceof EventDay){
                EventDay eventDay = (EventDay)event;
                getSupportActionBar().setTitle(getFormattedDate(eventDay.getCalendar().getTime()));
            }
        }
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.note = (TextView) findViewById(R.id.note);
    }
}
