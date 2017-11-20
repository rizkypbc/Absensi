package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.TutupSesiInterface;

/**
 * Created by ASUS on 12/08/2017.
 */

public class TutupSesiService {

    private TutupSesiInterface tutupSesiInterface;

    public TutupSesiService(Context context) {

        tutupSesiInterface = RetrofitBuilder.builder(context)
                .create(TutupSesiInterface.class);
    }

    public void doTutupSesi(String id_sesi, String materi, String keterangan, Callback callback) {

        tutupSesiInterface.tutupSesi(id_sesi, materi, keterangan).enqueue(callback);
    }
}
