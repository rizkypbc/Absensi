package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.BukaSesiTanpaLokasiInterface;

/**
 * Created by ASUS on 15/10/2017.
 */

public class BukaSesiTanpaLokasiService {

    private BukaSesiTanpaLokasiInterface bukaSesiTanpaLokasiInterface;

    public BukaSesiTanpaLokasiService(Context context) {

        bukaSesiTanpaLokasiInterface = RetrofitBuilder.builder(context)
                .create(BukaSesiTanpaLokasiInterface.class);
    }

    public void doBukaSesiTanpaLokasi(String materi, String nama_dosen, String pertemuan, String ruang, String keterangan, String kode_matkul, Callback callback) {

        bukaSesiTanpaLokasiInterface.getBukaSesiTanpaLokasi(materi, nama_dosen, pertemuan, ruang, keterangan, kode_matkul).enqueue(callback);
    }
}
