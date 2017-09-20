package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.IzinMahasiswaInterface;

/**
 * Created by ASUS on 18/09/2017.
 */

public class IzinMahasiswaService {

    private IzinMahasiswaInterface izinMahasiswaInterface;

    public IzinMahasiswaService(Context context) {

        izinMahasiswaInterface = RetrofitBuilder.builder(context)
                .create(IzinMahasiswaInterface.class);
    }

    public void doIzinMahasiswa(String nim, String nama, String jurusan, String id_sesi, Callback callback) {

        izinMahasiswaInterface.izinMahasiswa(nim, nama, jurusan, id_sesi).enqueue(callback);
    }
}
