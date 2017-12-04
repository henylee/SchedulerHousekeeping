package tje.co.kr.schedulerhousekeeping;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Calendar tempCal = (Calendar) intent.getSerializableExtra(MainActivity.EVENT);
        Log.d("tempCal", tempCal.toString());

        String tempDay = getFormattedDate(tempCal.getTime());
        setTitle(tempDay);

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
