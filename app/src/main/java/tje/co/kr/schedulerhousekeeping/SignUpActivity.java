package tje.co.kr.schedulerhousekeeping;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import tje.co.kr.schedulerhousekeeping.util.ServerUtil;

public class SignUpActivity extends BaseActivity {

    boolean isIdDupl = true;

    private android.widget.EditText idEdt;
    private android.widget.Button idDupBtn;
    private android.widget.EditText pwEdt;
    private android.widget.EditText nameEdt;
    private android.widget.EditText phoneEdt;
    private android.widget.Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        idDupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.id_dupi_ok(mContext, idEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        try {
                            isIdDupl = json.getBoolean("result");

                            if (!isIdDupl) {
                                AlertDialog.Builder myBuilder = new AlertDialog.Builder(mContext);
                                myBuilder.setTitle("중복 확인");
                                myBuilder.setMessage("이미 사용중인 아이디입니다.");
                                myBuilder.setPositiveButton("확인", null);
                                myBuilder.show();

                            } else {
                                Toast.makeText(mContext, "사용해도 좋은 아이디입니다.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isIDOk = !idEdt.getText().toString().equals("");
                if (!isIDOk) {
                    Toast.makeText(mContext, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isPwOk = !pwEdt.getText().toString().equals("");
                if (!isPwOk) {
                    Toast.makeText(mContext, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isNameOk = !nameEdt.getText().toString().equals("");
                if (!isNameOk) {
                    Toast.makeText(mContext, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isPhoneNumOk = !phoneEdt.getText().toString().equals("");
                if (!isPhoneNumOk) {
                    Toast.makeText(mContext, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                ServerUtil.sign_up(mContext, idEdt.getText().toString(), pwEdt.getText().toString(), nameEdt.getText().toString(), phoneEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Toast.makeText(mContext, "회원가입을 성공했습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.signupBtn = (Button) findViewById(R.id.signupBtn);
        this.phoneEdt = (EditText) findViewById(R.id.phoneEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idDupBtn = (Button) findViewById(R.id.idDupBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
    }
}
