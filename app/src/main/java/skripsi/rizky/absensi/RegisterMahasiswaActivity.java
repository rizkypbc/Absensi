package skripsi.rizky.absensi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.network.RegisterMahasiswaService;

public class RegisterMahasiswaActivity extends AppCompatActivity {

    private EditText txtNIM;
    private EditText txtNama;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtJurusan;
    private EditText txtProdi;

    private Button btnDaftarMahasiswa;

    private RegisterMahasiswaService registerMahasiswaService;

    public static void start(Context context){

        Intent intent = new Intent(context, RegisterMahasiswaActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtNIM = (EditText)findViewById(R.id.nim);
        txtNama = (EditText)findViewById(R.id.nama);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtJurusan = (EditText)findViewById(R.id.txtJurusan);
        txtProdi = (EditText)findViewById(R.id.txtProdi);

        btnDaftarMahasiswa = (Button)findViewById(R.id.btn_daftar_mahasiswa);

        btnDaftarMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAct();
            }
        });
    }

    void registerAct(){
        String nim = txtNIM.getText().toString();
        String nama = txtNama.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        String jurusan = txtJurusan.getText().toString();
        String prodi = txtProdi.getText().toString();

        if (TextUtils.isEmpty(nim)){
            txtNIM.setError("NIM tidak boleh kosong");
            return;
        }
        if (TextUtils.isEmpty(nama)){
            txtNama.setError("Nama tidak boleh kosong");
            return;
        }
        if (TextUtils.isEmpty(email)){
            txtEmail.setError("Email tidak boleh kosong");
            return;
        }
        if (TextUtils.isEmpty(password)){
            txtPassword.setError("Password tidak boleh kosong");
            return;
        }
        if (TextUtils.isEmpty(jurusan)){
            txtJurusan.setError("Jurusan tidak boleh kosong");
            return;
        }
        if (TextUtils.isEmpty(prodi)){
            txtProdi.setError("Prodi tidak boleh kosong");
            return;
        }

        registerMahasiswaService = new RegisterMahasiswaService(this);
        registerMahasiswaService.doRegisterMahasiswa(nim, nama, email, password, jurusan, prodi, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                BaseResponse baseResponse = (BaseResponse) response.body();

                if (baseResponse != null){
                    if (!baseResponse.isError()){

                        LoginMahasiswaActivity.start(RegisterMahasiswaActivity.this);
                        RegisterMahasiswaActivity.this.finish();
                    }

                    Toast.makeText(RegisterMahasiswaActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(RegisterMahasiswaActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
