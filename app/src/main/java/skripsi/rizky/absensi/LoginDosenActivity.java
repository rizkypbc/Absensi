package skripsi.rizky.absensi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.rizky.absensi.model.Dosen;
import skripsi.rizky.absensi.network.LoginDosenService;
import skripsi.rizky.absensi.util.PrefUtilDosen;

public class LoginDosenActivity extends AppCompatActivity {

    private EditText txtNIP;
    private EditText txtPassword;
    private Button btnLoginDosen;
    private TextView registerDosen;

    private LoginDosenService loginDosenService;

    public static void start(Context context){

        Intent intent = new Intent(context, LoginDosenActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dosen);

        if (isSessionLoginDosen()){
            DosenActivity.start(this);
            LoginDosenActivity.this.finish();
        }

        txtNIP = (EditText)findViewById(R.id.nip);
        txtPassword = (EditText)findViewById(R.id.password_dosen);
//        registerDosen = (TextView)findViewById(R.id.register_dosen);
        btnLoginDosen = (Button)findViewById(R.id.loginDosen);

        btnLoginDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActDosen();
            }
        });

//        String captionDosen = "Dont have a account? <b>Register<b>";
//        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(Html.fromHtml(captionDosen));
//        spannableStringBuilder.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View view) {
//                RegisterDosenActivity.start(LoginDosenActivity.this);
//            }
//        }, captionDosen.indexOf("Register") -3, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.WHITE), captionDosen
//        .indexOf("Register") -3, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        registerDosen.setText(spannableStringBuilder);
//        registerDosen.setMovementMethod(LinkMovementMethod.getInstance());
    }

    void loginActDosen(){

        String nip = txtNIP.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(nip)){
            txtNIP.setError("NIP tidak boleh kosong");
            return;
        }

        if (TextUtils.isEmpty(password)){
            txtPassword.setError("Password tidak boleh kosong");
            return;
        }

        loginDosenService = new LoginDosenService(this);
        loginDosenService.doLoginDosen(nip, password, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                Dosen dosen = (Dosen) response.body();

                if (dosen != null){
                    if (!dosen.isError()){
                        PrefUtilDosen.putDosen(LoginDosenActivity.this, PrefUtilDosen.DOSEN_SESSION, dosen);
                        DosenActivity.start(LoginDosenActivity.this);
                        LoginDosenActivity.this.finish();
                    }

                    Toast.makeText(LoginDosenActivity.this, dosen.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {


                Toast.makeText(LoginDosenActivity.this, "An error occured!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean isSessionLoginDosen(){

        return PrefUtilDosen.getDosen(this, PrefUtilDosen.DOSEN_SESSION) != null;
    }
}
