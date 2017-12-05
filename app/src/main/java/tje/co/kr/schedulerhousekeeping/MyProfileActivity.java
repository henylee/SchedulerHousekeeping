package tje.co.kr.schedulerhousekeeping;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.support.v7.app.AlertDialog;

import tje.co.kr.schedulerhousekeeping.util.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private android.widget.TextView idTxt;
    private android.widget.TextView nameTxt;
    private android.widget.TextView phoneTxt;
    private android.widget.Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("로그아웃 하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContextUtil.logout(mContext);

                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("취소", null);
//                만들어진 경고창을 띄운다.
                alert.show();
            }
        });
    }

    @Override
    public void setValues() {
        idTxt.setText(ContextUtil.getUserId(mContext));
        nameTxt.setText(ContextUtil.getUserName(mContext));
        phoneTxt.setText(ContextUtil.getUserPhone(mContext));
    }

    @Override
    public void bindViews() {
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.phoneTxt = (TextView) findViewById(R.id.phoneTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
    }
}
