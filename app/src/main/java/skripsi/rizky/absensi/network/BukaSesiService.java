package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.BukaSesi;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.BukaSesiInterface;

/**
 * Created by ASUS on 08/08/2017.
 */

public class BukaSesiService {

    private BukaSesiInterface bukaSesiInterface;

    public BukaSesiService(Context context) {

        bukaSesiInterface = RetrofitBuilder.builder(context)
                .create(BukaSesiInterface.class);
    }

    public void doBukaSesi(String materi, String nama_dosen, String pertemuan, String ruang, String keterangan, String kode_matkul, Callback callback) {

        bukaSesiInterface.getBukaSesi(materi, nama_dosen, pertemuan, ruang, keterangan, kode_matkul).enqueue(callback);
    }
}
