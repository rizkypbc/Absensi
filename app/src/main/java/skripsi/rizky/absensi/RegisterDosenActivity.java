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
import skripsi.rizky.absensi.network.RegisterDosenService;
import skripsi.rizky.absensi.network.interfaces.RegisterDosenInterface;

public class RegisterDosenActivity extends AppCompatActivity {

    private EditText txtNIP;
    private EditText txtNama;
    private EditText txtPassword;
    private EditText txtEmail;
    private EditText txtJurusan;
    private EditText txtProdi;

    private Button btnDaftarDosen;

    private RegisterDosenService registerDosenService;

    public static void start(Context context){

        Intent intent = new Intent(context, RegisterDosenActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dosen);

        txtNIP = (EditText)findViewById(R.id.nip);
        txtNama = (EditText)findViewById(R.id.nama_dosen);
        txtEmail = (EditText)findViewById(R.id.txtEmailDosen);
        txtPassword = (EditText)findViewById(R.id.txtPasswordDosen);
        txtJurusan = (EditText)findViewById(R.id.txtJurusanDosen);
        txtProdi = (EditText)findViewById(R.id.txtProdiDosen);

        btnDaftarDosen = (Button)findViewById(R.id.btn_daftar_dosen);

        btnDaftarDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerActDosen();
            }
        });
    }

    void registerActDosen(){

        String nip = txtNIP.getText().toString();
        String nama = txtNama.getText().toString();
        String password = txtPassword.getText().toString();
        String email = txtEmail.getText().toString();
        String jurusan = txtJurusan.getText().toString();
        String prodi = txtProdi.getText().toString();

        if (TextUtils.isEmpty(nip)){
            txtNIP.setError("NIP tidak boleh kosong");
            return;
        }
        if (TextUtils.isEmpty(nama)){
            txtNama.setError("Nama tidak boleh kosong");
            return;
        }
        if (TextUtils.isEmpty(password)){
            txtPassword.setError("Password tidak boleh kosong");
            return;
        }
        if (TextUtils.isEmpty(email)){
            txtEmail.setError("Email tidak boleh kosong");
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

        registerDosenService = new RegisterDosenService(this);
        registerDosenService.doRegisterDosen(nip, nama, password, email, jurusan, prodi, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                BaseResponse baseResponse = (BaseResponse) response.body();

                if (baseResponse != null){
                    if (!baseResponse.isError()){

                        LoginDosenActivity.start(RegisterDosenActivity.this);
                        RegisterDosenActivity.this.finish();
                    }

                    Toast.makeText(RegisterDosenActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {


                Toast.makeText(RegisterDosenActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
