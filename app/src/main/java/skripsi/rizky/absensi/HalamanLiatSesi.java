package skripsi.rizky.absensi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HalamanLiatSesi extends AppCompatActivity {

    private ImageView btnListAbsenSiswa, btnVerifikasiAbsen;

    public static void start(Context context) {

        Intent intent = new Intent(context, HalamanLiatSesi.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_liat_sesi);

        btnListAbsenSiswa = (ImageView) findViewById(R.id.btnListAbsenSiswa);
        btnVerifikasiAbsen = (ImageView) findViewById(R.id.btnVerifikasiAbsen);

        btnListAbsenSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HalamanLiatSesi.this, ListAbsen.class));
            }
        });

        btnVerifikasiAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HalamanLiatSesi.this, ListVerfikasiAbsen.class));
            }
        });
    }

}
