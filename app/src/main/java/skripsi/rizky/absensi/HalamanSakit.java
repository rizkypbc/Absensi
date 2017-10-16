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
import skripsi.rizky.absensi.model.Mahasiswa;
import skripsi.rizky.absensi.model.Sakit;
import skripsi.rizky.absensi.model.SakitData;
import skripsi.rizky.absensi.network.SakitMahasiswaService;
import skripsi.rizky.absensi.util.PrefUtilMahasiswa;

public class HalamanSakit extends AppCompatActivity {

    private Button btnSakit;
    private SakitMahasiswaService sakitMahasiswaService;
    private RadioGroup radioJurusanGroup;
    private RadioButton radioJurusanButton;
    private EditText mataKuliahSakit, textRuangSakit, pertemuanSakit, pilihSesiSakit;

    public static void start(Context context) {

        Intent intent = new Intent(context, HalamanSakit.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_sakit);

        radioJurusanGroup = (RadioGroup) findViewById(R.id.radioJurusanSakit);
        pilihSesiSakit = (EditText) findViewById(R.id.pilihSesiSakit);
        btnSakit = (Button) findViewById(R.id.btnSakit);
        mataKuliahSakit = (EditText) findViewById(R.id.mataKuliahSakit);
        textRuangSakit = (EditText) findViewById(R.id.textRuangMahasiswaSakit);
        pertemuanSakit = (EditText) findViewById(R.id.pertemuanSakit);

        pilihSesiSakit.setEnabled(false);
        mataKuliahSakit.setEnabled(false);
        pertemuanSakit.setEnabled(false);
        textRuangSakit.setEnabled(false);

        if (getIntent().getSerializableExtra("sakit") != null) {

            SakitData sakitData = (SakitData) getIntent().getSerializableExtra("sakit");
            pilihSesiSakit.setText(sakitData.getId_sesi());
            mataKuliahSakit.setText(sakitData.getKode_matkul());
            pertemuanSakit.setText(sakitData.getPertemuan());
            textRuangSakit.setText(sakitData.getRuang());
        }

        btnSakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendSakit();
            }
        });
    }

    private void sendSakit() {

        Mahasiswa mahasiswa = PrefUtilMahasiswa.getMahasiswa(getApplicationContext(), PrefUtilMahasiswa.MAHASISWA_SESSION);

        String nimMahasiswa = mahasiswa.getMahasiswaData().getNim();
        String namaMahasiswa = mahasiswa.getMahasiswaData().getNama();

        int selectedId = radioJurusanGroup.getCheckedRadioButtonId();
        radioJurusanButton = (RadioButton) findViewById(selectedId);
        String jurusan = radioJurusanButton.getText().toString();

        String pilihKelasSakit = pilihSesiSakit.getText().toString();

        sakitMahasiswaService = new SakitMahasiswaService(getApplicationContext());
        sakitMahasiswaService.doSakitMahasiswa(nimMahasiswa, namaMahasiswa, jurusan, pilihKelasSakit, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                BaseResponse baseResponse = (BaseResponse) response.body();
                if (baseResponse != null) {

                    if (!baseResponse.isError()) {

                        HalamanMahasiswa.start(HalamanSakit.this);
                        HalamanSakit.this.finish();
                    }

                    Toast.makeText(HalamanSakit.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(HalamanSakit.this, "An Error Occure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}