package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.GetLocationMahasiswaInterface;

/**
 * Created by ASUS on 04/08/2017.
 */

public class GetLocationMahasiswaService {

    private GetLocationMahasiswaInterface getLocationMahasiswaInterface;

    public GetLocationMahasiswaService(Context context) {

        getLocationMahasiswaInterface = RetrofitBuilder.builder(context)
                .create(GetLocationMahasiswaInterface.class);
    }

    public void doGetLocationMahasiswa(String nim, String nama, String jurusan, String kelas, double latitude, double longitude, Callback callback) {

        getLocationMahasiswaInterface.getLocationMahasiswa(nim, nama, jurusan, kelas, latitude, longitude).enqueue(callback);
    }
}
