package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.LoginMahasiswaInterface;

/**
 * Created by ASUS on 12/07/2017.
 */

public class LoginMahasiswaService {

    private LoginMahasiswaInterface loginMahasiswaInterface;

    public LoginMahasiswaService(Context context){

        loginMahasiswaInterface = RetrofitBuilder.builder(context)
                .create(LoginMahasiswaInterface.class);
    }

    public void doLoginMahasiswa(String nim, String password, Callback callback){

        loginMahasiswaInterface.loginMahasiswa(nim, password).enqueue(callback);
    }
}
