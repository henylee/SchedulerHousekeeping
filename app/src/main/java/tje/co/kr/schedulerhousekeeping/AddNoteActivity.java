package tje.co.kr.schedulerhousekeeping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.applandeo.materialcalendarview.CalendarView;

public class AddNoteActivity extends BaseActivity {

    private com.applandeo.materialcalendarview.CalendarView datePicker;
    private android.support.v7.widget.AppCompatButton addNoteButton;
    private android.widget.EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                MyEventDay myEventDay = new MyEventDay(datePicker.getSelectedDate(),
                        R.drawable.ic_message_black_48dp, noteEditText.getText().toString());
                returnIntent.putExtra(MainActivity.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.noteEditText = (EditText) findViewById(R.id.noteEditText);
        this.addNoteButton = (AppCompatButton) findViewById(R.id.addNoteButton);
        this.datePicker = (CalendarView) findViewById(R.id.datePicker);
    }
}
