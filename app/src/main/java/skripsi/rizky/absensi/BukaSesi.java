package skripsi.rizky.absensi;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.rizky.absensi.matakuliah.Downloader;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.model.Dosen;
import skripsi.rizky.absensi.network.BukaSesiService;
import skripsi.rizky.absensi.network.GetLocationMahasiswaService;
import skripsi.rizky.absensi.ruang.DownloaderRuang;
import skripsi.rizky.absensi.util.PrefUtilDosen;

public class BukaSesi extends AppCompatActivity {

    final static String API_MATAKULIAH = "http://192.168.43.212/absensi/MataKuliah/mataKuliah.php";
    final static String API_RUANG = "http://192.168.43.212/absensi/MataKuliah/ruang.php";

    //    final static String API_MATAKULIAH = "http://aksesblk-samarinda.com/absensi/MataKuliah/mataKuliah.php";
//    final static String API_RUANG = "http://aksesblk-samarinda.com/absensi/MataKuliah/ruang.php";
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;
    private BukaSesiService bukaSesiService;
    private Spinner spnMatkul, spnStatus, spnKelas;
    private Button btnBukaKelas, btnCekLokasiSesi;
    private EditText txtMateri, txtPertemuanSesi;
    private GetLocationMahasiswaService getLocationMahasiswaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buka_sesi);

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

        spnStatus = (Spinner) findViewById(R.id.spnStatus);
        spnMatkul = (Spinner) findViewById(R.id.spnMatKul);
        btnBukaKelas = (Button) findViewById(R.id.btnBukaSesi);
        txtMateri = (EditText) findViewById(R.id.txtMateri);
        txtPertemuanSesi = (EditText) findViewById(R.id.txtPertemuanSesi);
        spnKelas = (Spinner) findViewById(R.id.spnKelas);
        btnCekLokasiSesi = (Button) findViewById(R.id.btnCekLokasiSesi);

        btnBukaKelas.setEnabled(false);
        btnCekLokasiSesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String pertemuanSesi = txtPertemuanSesi.getText().toString();
//
//                if (TextUtils.isEmpty(pertemuanSesi)) {
//                    txtPertemuanSesi.setError("Field Pertemuan tidak boleh kosong");
//                    return;
//                }
//
//                String materi = txtMateri.getText().toString();
//
//                if (TextUtils.isEmpty(materi)) {
//                    txtMateri.setError("Field Materi tidak boleh kosong");
//                    return;
//                }
//
//                Dosen dosen = PrefUtilDosen.getDosen(getApplicationContext(), PrefUtilDosen.DOSEN_SESSION);
//                String namaDosen = dosen.getDosenData().getNama();
//
//
//                String ruang = spnKelas.getSelectedItem().toString();
//                String matkul = spnMatkul.getSelectedItem().toString();
//                String keterangan = spnStatus.getSelectedItem().toString();
//
//                bukaSesiService = new BukaSesiService(getApplicationContext());
//                bukaSesiService.doBukaSesi(materi, namaDosen, pertemuanSesi, ruang, keterangan, matkul, new Callback() {
//                    @Override
//                    public void onResponse(Call call, Response response) {
//
//                        BaseResponse baseResponse = (BaseResponse) response.body();
//
//                        if (baseResponse != null) {
//                            if (!baseResponse.isError()) {
//
//                                DosenActivity.start(BukaSesi.this);
//                                BukaSesi.this.finish();
//                            }
//
//                            Toast.makeText(BukaSesi.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//
//                        Toast.makeText(BukaSesi.this, "An error occured", Toast.LENGTH_SHORT).show();
//                    }
//                });

                gps = new GPSTracker(BukaSesi.this);

                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    if (longitude == -18.5333) {

                        Toast.makeText(BukaSesi.this, "Anda sudah dapat membuka kelas", Toast.LENGTH_SHORT).show();

                        btnBukaKelas.setEnabled(true);
                        getSesi();

                    } else if (longitude == 117.1549676) {
                        Toast.makeText(BukaSesi.this, "Anda sudah dapat membuka kelas", Toast.LENGTH_SHORT).show();
                        btnBukaKelas.setEnabled(true);
                        getSesi();
                    } else {

                        Toast.makeText(BukaSesi.this, "Anda belum berada di lokasi dan ruang yang sesuai\n Coba cari lokasi yang sesuai", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    gps.showSettingsAlert();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Downloader(BukaSesi.this, API_MATAKULIAH, spnMatkul).execute();
        new DownloaderRuang(BukaSesi.this, API_RUANG, spnKelas).execute();
    }

    private void bukaSesi() {

        String pertemuanSesi = txtPertemuanSesi.getText().toString();

        if (TextUtils.isEmpty(pertemuanSesi)) {
            txtPertemuanSesi.setError("Field Pertemuan tidak boleh kosong");
            return;
        }

        String materi = txtMateri.getText().toString();

        if (TextUtils.isEmpty(materi)) {
            txtMateri.setError("Field Materi tidak boleh kosong");
            return;
        }

        Dosen dosen = PrefUtilDosen.getDosen(getApplicationContext(), PrefUtilDosen.DOSEN_SESSION);
        String namaDosen = dosen.getDosenData().getNama();


        String ruang = spnKelas.getSelectedItem().toString();
        String matkul = spnMatkul.getSelectedItem().toString();
        String keterangan = spnStatus.getSelectedItem().toString();

        bukaSesiService = new BukaSesiService(getApplicationContext());
        bukaSesiService.doBukaSesi(materi, namaDosen, pertemuanSesi, ruang, keterangan, matkul, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                BaseResponse baseResponse = (BaseResponse) response.body();

                if (baseResponse != null) {
                    if (!baseResponse.isError()) {

                        DosenActivity.start(BukaSesi.this);
                        BukaSesi.this.finish();
                    }

                    Toast.makeText(BukaSesi.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(BukaSesi.this, "An error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSesi() {


        btnBukaKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bukaSesi();
            }
        });
    }
}
