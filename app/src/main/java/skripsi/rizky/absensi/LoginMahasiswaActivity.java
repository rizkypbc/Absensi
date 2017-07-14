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
import skripsi.rizky.absensi.model.Mahasiswa;
import skripsi.rizky.absensi.network.LoginMahasiswaService;
import skripsi.rizky.absensi.util.PrefUtilMahasiswa;

public class LoginMahasiswaActivity extends AppCompatActivity {

    private EditText txtNIM;
    private EditText txtPassword;
    private Button btnLoginMahasiswa;
    private TextView registerCaption;

    private LoginMahasiswaService loginMahasiswaService;

    public static void start(Context context){

        Intent intent = new Intent(context, LoginMahasiswaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (isSessionLoginMahasiswa()){
            MahasiswaActivity.start(this);
            LoginMahasiswaActivity.this.finish();
        }

        txtNIM = (EditText)findViewById(R.id.nim);
        txtPassword = (EditText)findViewById(R.id.password);
        btnLoginMahasiswa = (Button)findViewById(R.id.loginMahasiswa);
        registerCaption = (TextView)findViewById(R.id.register_mahasiwa);

        btnLoginMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAct();
            }
        });

        String caption = "Dont have a account? <b>Register<b>";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(Html.fromHtml(caption));
        spannableStringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                RegisterMahasiswaActivity.start(LoginMahasiswaActivity.this);
            }
        }, caption.indexOf("Register") - 3, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.WHITE), caption
        .indexOf("Register") -3, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        registerCaption.setText(spannableStringBuilder);
        registerCaption.setMovementMethod(LinkMovementMethod.getInstance());
    }

    void loginAct(){

        String nim = txtNIM.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(nim)){
            txtNIM.setError("NIM tidak boleh kosong");
            return;
        }

        if (TextUtils.isEmpty(password)){
            txtPassword.setError("Password tidak boleh kosong");
            return;
        }

        loginMahasiswaService = new LoginMahasiswaService(this);
        loginMahasiswaService.doLoginMahasiswa(nim, password, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                Mahasiswa mahasiswa = (Mahasiswa) response.body();

                if (mahasiswa != null){
                    if (!mahasiswa.isError()){
                        PrefUtilMahasiswa.putMahasiswa(LoginMahasiswaActivity.this, PrefUtilMahasiswa.MAHASISWA_SESSION, mahasiswa);
                        MahasiswaActivity.start(LoginMahasiswaActivity.this);
                        LoginMahasiswaActivity.this.finish();
                    }

                    Toast.makeText(LoginMahasiswaActivity.this, mahasiswa.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(LoginMahasiswaActivity.this, "An error occured!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean isSessionLoginMahasiswa(){
        return PrefUtilMahasiswa.getMahasiswa(this, PrefUtilMahasiswa.MAHASISWA_SESSION) != null;
    }
}
