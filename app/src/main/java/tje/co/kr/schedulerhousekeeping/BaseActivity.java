package tje.co.kr.schedulerhousekeeping;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

/**
 * Created by joeun on 2017-11-22.
 */

public abstract class BaseActivity extends AppCompatActivity {

    Context mContext=this;

    public ImageView hamburgBtn;

    public abstract void setupEvents();
    public abstract void setValues();
    public abstract void bindViews();

    public void setCustomActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        hamburgBtn = getSupportActionBar().getCustomView().findViewById(R.id.hamburgBtn);
        Toolbar parent = (Toolbar) getSupportActionBar().getCustomView().getParent();
        parent.setContentInsetsAbsolute(0, 0);
        getSupportActionBar().setElevation(0);
    }

}
