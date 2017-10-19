package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.VerifikasiAbsenInterface;

/**
 * Created by ASUS on 20/10/2017.
 */

public class VerifikasiAbsenService {

    private VerifikasiAbsenInterface verifikasiAbsenInterface;

    public VerifikasiAbsenService(Context context) {

        verifikasiAbsenInterface = RetrofitBuilder.builder(context)
                .create(VerifikasiAbsenInterface.class);
    }

    public void doVerifikasiAbsen(String id, String token_absen, Callback callback) {

        verifikasiAbsenInterface.verifikasiAbsen(id, token_absen).enqueue(callback);
    }
}


//    public void doTutupSesi(String id_sesi, String materi, String keterangan, Callback callback){
//
//        tutupSesiInterface.tutupSesi(id_sesi, materi, keterangan).enqueue(callback);
//    }
//}
