package skripsi.rizky.absensi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import skripsi.rizky.absensi.model.Mahasiswa;
import skripsi.rizky.absensi.util.PrefUtilMahasiswa;

public class MahasiswaActivity extends AppCompatActivity {


    private TextView greeting;
    private TextView nim;
    private Button btnLogout;


    public static void start(Context context){

        Intent intent = new Intent(context, MahasiswaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        greeting = (TextView)findViewById(R.id.greeting);
        nim = (TextView)findViewById(R.id.nim);
        btnLogout = (Button)findViewById(R.id.btn_logout);

        Mahasiswa mahasiswa = PrefUtilMahasiswa.getMahasiswa(this, PrefUtilMahasiswa.MAHASISWA_SESSION);

        greeting.setText(getResources().getString(R.string.greeting, mahasiswa.getMahasiswaData().getNama()));
        nim.setText(mahasiswa.getMahasiswaData().getNim());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutAct();

                LoginMahasiswaActivity.start(MahasiswaActivity.this);
                MahasiswaActivity.this.finish();
            }
        });
    }

    void logoutAct(){
        PrefUtilMahasiswa.clear(this);
    }

}
