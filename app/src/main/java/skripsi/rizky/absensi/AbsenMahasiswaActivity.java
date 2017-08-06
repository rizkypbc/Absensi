package skripsi.rizky.absensi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
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
import skripsi.rizky.absensi.network.GetLocationMahasiswaService;
import skripsi.rizky.absensi.util.PrefUtilMahasiswa;

public class AbsenMahasiswaActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;
    private Button btnGetLocation, btnAbsensi;
    private GetLocationMahasiswaService getLocationMahasiswaService;
    private Spinner spnKelas;
    private RadioGroup radioJurusanGroup;
    private RadioButton radioJurusanButton;


    public static void start(Context context){


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
        spnKelas = (Spinner) findViewById(R.id.spnKelas);
        btnAbsensi = (Button) findViewById(R.id.btnAbsen);
        btnAbsensi.setEnabled(false);

        btnGetLocation = (Button) findViewById(R.id.btnGetLocation);


//        btnGetLocation.setVisibility(View.INVISIBLE);

//        Dosen dosen = PrefUtilDosen.getDosen(this, PrefUtilDosen.DOSEN_SESSION);
//
//        if (dosen.equals("dosen_session")){
//
//            btnGetLocation.setVisibility(View.VISIBLE);
//        } else {
//
//            btnGetLocation.setVisibility(View.INVISIBLE);
//        }

        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gps = new GPSTracker(AbsenMahasiswaActivity.this);

                // Check if gps enabled
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " +
                            latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show();

                    if (longitude == -18.5333) {

                        btnAbsensi.setEnabled(true);
                        btnAbsensi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                //sendLocation();
                                double latitude = gps.getLatitude();
                                double longitude = gps.getLongitude();

                                Mahasiswa mahasiswa = PrefUtilMahasiswa.getMahasiswa(getApplicationContext(), PrefUtilMahasiswa.MAHASISWA_SESSION);

                                String nimMahasiswa = mahasiswa.getMahasiswaData().getNim();
                                String namaMahasiswa = mahasiswa.getMahasiswaData().getNama();

                                int selectedId = radioJurusanGroup.getCheckedRadioButtonId();
                                radioJurusanButton = (RadioButton) findViewById(selectedId);
                                String jurusan = radioJurusanButton.getText().toString();

                                String kelas = spnKelas.getSelectedItem().toString();

                                getLocationMahasiswaService = new GetLocationMahasiswaService(getApplicationContext());
                                getLocationMahasiswaService.doGetLocationMahasiswa(nimMahasiswa, namaMahasiswa, jurusan, kelas, latitude, longitude, new Callback() {

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
                        });
                    } else {

                        btnAbsensi.setEnabled(false);
                    }
                } else {

                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
            }
        });
//




    }


}
