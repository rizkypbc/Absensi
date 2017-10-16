package skripsi.rizky.absensi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.model.Mahasiswa;
import skripsi.rizky.absensi.model.SesiData;
import skripsi.rizky.absensi.network.GetLocationMahasiswaService;
import skripsi.rizky.absensi.util.PrefUtilMahasiswa;

public class AbsenMahasiswaActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;
    private Button btnGetLocation, btnAbsensi;
    private GetLocationMahasiswaService getLocationMahasiswaService;
    private RadioGroup radioJurusanGroup;
    private RadioButton radioJurusanButton;
    private EditText mataKuliah, textRuang, pertemuan, pilihSesi;


    public static void start(Context context) {


        Intent intent = new Intent(context, AbsenMahasiswaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen_mahasiswa);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will
//                execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        radioJurusanGroup = (RadioGroup) findViewById(R.id.radioJurusan);

        pilihSesi = (EditText) findViewById(R.id.pilihSesi);
        btnAbsensi = (Button) findViewById(R.id.btnAbsen);
        mataKuliah = (EditText) findViewById(R.id.mataKuliah);
        textRuang = (EditText) findViewById(R.id.textRuangMahasiswa);
        pertemuan = (EditText) findViewById(R.id.pertemuan);

        pilihSesi.setEnabled(false);
        mataKuliah.setEnabled(false);
        pertemuan.setEnabled(false);
        btnAbsensi.setEnabled(false);
        textRuang.setEnabled(false);

        btnGetLocation = (Button) findViewById(R.id.btnGetLocation);

        if (getIntent().getSerializableExtra("kelas") != null) {

            SesiData sesiData = (SesiData) getIntent().getSerializableExtra("kelas");
            pilihSesi.setText(sesiData.getId_sesi());
            mataKuliah.setText(sesiData.getKode_matkul());
            pertemuan.setText(sesiData.getPertemuan());
            textRuang.setText(sesiData.getRuang());

        }


        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gps = new GPSTracker(AbsenMahasiswaActivity.this);

                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    if (longitude == 117.1494533) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();

                        btnAbsensi.setEnabled(true);
                        getLocation();

//                    if (latitude == -0.4727658 && longitude == 117.1534053 && "405".equals(textRuang.getText().toString())) {
//
//                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
//
//                        btnAbsensi.setEnabled(true);
//                        getLocation();

                    } else if (longitude == 117.1549676) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else if (longitude == 117.1538648) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else if (longitude == 117.15482827) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else if (longitude == -18.5333) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else if (latitude == -0.4727905 && longitude == 117.1533938 && "405".equals(textRuang.getText().toString())) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else if (latitude == -0.4727493 && longitude == 117.1534283 && "406".equals(textRuang.getText().toString())) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else if (latitude == -0.4737512 && longitude == 117.1534972 && "406".equals(textRuang.getText().toString())) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else if (latitude == -0.4727731 && longitude == 117.1536236 && "407".equals(textRuang.getText().toString())) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else if (latitude == -0.4727576 && longitude == 117.1534168 && "407".equals(textRuang.getText().toString())) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else if (latitude == -0.4728893 && longitude == 117.1533479 && "407".equals(textRuang.getText().toString())) {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda sudah dapat melakukan absensi", Toast.LENGTH_SHORT).show();
                        btnAbsensi.setEnabled(true);
                        getLocation();
                    } else {

                        Toast.makeText(AbsenMahasiswaActivity.this, "Anda belum berada di lokasi dan ruang yang sesuai\n Coba cari lokasi yang sesuai", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    gps.showSettingsAlert();
                }
            }
        });
    }


    private void sendLocation() {

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        Mahasiswa mahasiswa = PrefUtilMahasiswa.getMahasiswa(getApplicationContext(), PrefUtilMahasiswa.MAHASISWA_SESSION);

        String nimMahasiswa = mahasiswa.getMahasiswaData().getNim();
        String namaMahasiswa = mahasiswa.getMahasiswaData().getNama();

        int selectedId = radioJurusanGroup.getCheckedRadioButtonId();
        radioJurusanButton = (RadioButton) findViewById(selectedId);
        String jurusan = radioJurusanButton.getText().toString();

        String pilihKelas = pilihSesi.getText().toString();

        getLocationMahasiswaService = new GetLocationMahasiswaService(getApplicationContext());
        getLocationMahasiswaService.doGetLocationMahasiswa(nimMahasiswa, namaMahasiswa, jurusan, latitude, longitude, pilihKelas, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                BaseResponse baseResponse = (BaseResponse) response.body();

                if (baseResponse != null) {
                    if (!baseResponse.isError()) {

                        HalamanMahasiswa.start(AbsenMahasiswaActivity.this);
                        AbsenMahasiswaActivity.this.finish();

                    }

                    Toast.makeText(AbsenMahasiswaActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    btnAbsensi.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(AbsenMahasiswaActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getLocation() {


        btnAbsensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendLocation();
            }
        });
    }


}