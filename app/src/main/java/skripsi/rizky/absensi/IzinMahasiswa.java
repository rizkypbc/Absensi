package skripsi.rizky.absensi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.model.IzinData;
import skripsi.rizky.absensi.model.Mahasiswa;
import skripsi.rizky.absensi.model.SesiData;
import skripsi.rizky.absensi.network.IzinMahasiswaService;
import skripsi.rizky.absensi.util.PrefUtilMahasiswa;

public class IzinMahasiswa extends AppCompatActivity {

    private Button btnIzin;
    private IzinMahasiswaService izinMahasiswaService;
    private RadioGroup radioJurusanGroup;
    private RadioButton radioJurusanButton;
    private EditText mataKuliahIzin, textRuangIzin, pertemuanIzin, pilihSesiIzin;

    public static void start(Context context) {

        Intent intent = new Intent(context, IzinMahasiswa.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izin_mahasiswa);

        radioJurusanGroup = (RadioGroup) findViewById(R.id.radioJurusanIzin);
        pilihSesiIzin = (EditText) findViewById(R.id.pilihSesiIzin);
        btnIzin = (Button) findViewById(R.id.btnIzin);
        mataKuliahIzin = (EditText) findViewById(R.id.mataKuliahIzin);
        textRuangIzin = (EditText) findViewById(R.id.textRuangMahasiswaIzin);
        pertemuanIzin = (EditText) findViewById(R.id.pertemuanIzin);

        pilihSesiIzin.setEnabled(false);
        mataKuliahIzin.setEnabled(false);
        pertemuanIzin.setEnabled(false);
        textRuangIzin.setEnabled(false);

        if (getIntent().getSerializableExtra("izin") != null) {

            IzinData izinData = (IzinData) getIntent().getSerializableExtra("izin");
            pilihSesiIzin.setText(izinData.getId_sesi());
            mataKuliahIzin.setText(izinData.getKode_matkul());
            pertemuanIzin.setText(izinData.getPertemuan());
            textRuangIzin.setText(izinData.getRuang());
        }

        btnIzin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendIzin();
            }
        });
    }

    private void sendIzin() {

        Mahasiswa mahasiswa = PrefUtilMahasiswa.getMahasiswa(getApplicationContext(), PrefUtilMahasiswa.MAHASISWA_SESSION);

        String nimMahasiswa = mahasiswa.getMahasiswaData().getNim();
        String namaMahasiswa = mahasiswa.getMahasiswaData().getNama();

        int selectedId = radioJurusanGroup.getCheckedRadioButtonId();
        radioJurusanButton = (RadioButton) findViewById(selectedId);
        String jurusan = radioJurusanButton.getText().toString();

        String pilihKelasIzin = pilihSesiIzin.getText().toString();

        izinMahasiswaService = new IzinMahasiswaService(getApplicationContext());
        izinMahasiswaService.doIzinMahasiswa(nimMahasiswa, namaMahasiswa, jurusan, pilihKelasIzin, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {


                BaseResponse baseResponse = (BaseResponse) response.body();

                if (baseResponse != null) {

                    if (!baseResponse.isError()) {

                        HalamanMahasiswa.start(IzinMahasiswa.this);
                        IzinMahasiswa.this.finish();
                    }

                    Toast.makeText(IzinMahasiswa.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(IzinMahasiswa.this, "An Error Occured", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
