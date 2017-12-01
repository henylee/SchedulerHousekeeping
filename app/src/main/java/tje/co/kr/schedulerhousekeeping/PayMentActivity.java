package tje.co.kr.schedulerhousekeeping;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

public class PayMentActivity extends BaseActivity {

    Calendar mReservationDate = Calendar.getInstance();
    private android.widget.RadioButton rbBtn1;
    private android.widget.RadioButton rbBtn2;
    private android.widget.RadioGroup payGroup;
    private android.widget.DatePicker datePicker;
    private android.widget.Spinner spiner1;
    private android.widget.EditText costEdt;
    private android.widget.Button saveBtn;
    private Button selectCaledarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ment);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        selectCaledarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mContext).show();
            }
        });

        se.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mReservationDate.set(Calendar.YEAR, year);
                        mReservationDate.set(Calendar.MONTH, month);
                        mReservationDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                    }
                }, mReservationDate.get(Calendar.YEAR),
                        mReservationDate.get(Calendar.MONTH),
                        mReservationDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.saveBtn = (Button) findViewById(R.id.saveBtn);
        this.costEdt = (EditText) findViewById(R.id.costEdt);
        this.spiner1 = (Spinner) findViewById(R.id.spiner1);
        this.selectCaledarBtn = (Button) findViewById(R.id.selectCaledarBtn);
        this.payGroup = (RadioGroup) findViewById(R.id.payGroup);
        this.rbBtn2 = (RadioButton) findViewById(R.id.rbBtn2);
        this.rbBtn1 = (RadioButton) findViewById(R.id.rbBtn1);
    }
}
