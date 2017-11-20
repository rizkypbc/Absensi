package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.LiatSesiInterface;

/**
 * Created by ASUS on 14/09/2017.
 */

public class LiatSesiService {

    private LiatSesiInterface liatSesiInterface;

    public LiatSesiService(Context context) {

        liatSesiInterface = RetrofitBuilder.builder(context)
                .create(LiatSesiInterface.class);
    }

    public void doLiatSesi(String nama_dosen, Callback callback) {

        liatSesiInterface.getLiatSesi(nama_dosen).enqueue(callback);
    }
}
