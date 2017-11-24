package tje.co.kr.schedulerhousekeeping;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

    }
}
