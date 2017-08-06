package skripsi.rizky.absensi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import skripsi.rizky.absensi.util.PrefUtilDosen;

public class MainActivity extends AppCompatActivity {


    private Button btnLogin, btnDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button)findViewById(R.id.btnGoLoginMahasiswa);
        btnDosen = (Button)findViewById(R.id.btnGoLoginDosen);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mahasiswa = new Intent(getApplicationContext(), LoginMahasiswaActivity.class);
                startActivity(mahasiswa);
            }
        });

        btnDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dosen = new Intent(getApplicationContext(), LoginDosenActivity.class);
                startActivity(dosen);
            }
        });
    }
}
