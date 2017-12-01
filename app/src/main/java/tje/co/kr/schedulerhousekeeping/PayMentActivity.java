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
import android.widget.Toast;

import java.util.Calendar;

import tje.co.kr.schedulerhousekeeping.data.Payment;
import tje.co.kr.schedulerhousekeeping.util.GlobalData;

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
    private EditText storeEdt;

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
                new DatePickerDialog(mContext,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                mReservationDate.set(Calendar.YEAR, i);
                                mReservationDate.set(Calendar.MONTH, i1);
                                mReservationDate.set(Calendar.DAY_OF_MONTH, i2);

                            }
                        }, mReservationDate.get(Calendar.YEAR), mReservationDate.get(Calendar.MONTH), mReservationDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                GlobalData.mPay.add(new Payment(storeEdt.getText().toString(), ,costEdt.getText().toString()));
                finish();
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
        this.storeEdt = (EditText) findViewById(R.id.storeEdt);
        this.spiner1 = (Spinner) findViewById(R.id.spiner1);
        this.selectCaledarBtn = (Button) findViewById(R.id.selectCaledarBtn);
        this.payGroup = (RadioGroup) findViewById(R.id.payGroup);
        this.rbBtn2 = (RadioButton) findViewById(R.id.rbBtn2);
        this.rbBtn1 = (RadioButton) findViewById(R.id.rbBtn1);
    }
}
