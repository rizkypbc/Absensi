package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.RegisterMahasiswaInterface;

/**
 * Created by ASUS on 12/07/2017.
 */

public class RegisterMahasiswaService {

    private RegisterMahasiswaInterface registerMahasiswaInterface;

    public RegisterMahasiswaService(Context context){

        registerMahasiswaInterface = RetrofitBuilder.builder(context)
                .create(RegisterMahasiswaInterface.class);
    }

    public void doRegisterMahasiswa(String nim, String nama, String email, String password, String jurusan, String prodi, Callback callback){

        registerMahasiswaInterface.registerMahasiswa(nim, nama, email, password, jurusan, prodi).enqueue(callback);
    }
}
