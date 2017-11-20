package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.SakitMahasiswaInterface;

/**
 * Created by ASUS on 16/10/2017.
 */

public class SakitMahasiswaService {

    private SakitMahasiswaInterface sakitMahasiswaInterface;

    public SakitMahasiswaService(Context context) {

        sakitMahasiswaInterface = RetrofitBuilder.builder(context)
                .create(SakitMahasiswaInterface.class);
    }

    public void doSakitMahasiswa(String nim, String nama, String jurusan, String id_sesi, Callback callback) {

        sakitMahasiswaInterface.sakitMahasiswa(nim, nama, jurusan, id_sesi).enqueue(callback);
    }
}
