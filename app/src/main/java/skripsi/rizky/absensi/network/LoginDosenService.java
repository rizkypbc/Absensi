package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.LoginDosenInterface;

/**
 * Created by ASUS on 13/07/2017.
 */

public class LoginDosenService {

    private LoginDosenInterface loginDosenInterface;

    public LoginDosenService(Context context){

        loginDosenInterface = RetrofitBuilder.builder(context)
                .create(LoginDosenInterface.class);
    }

    public void doLoginDosen(String nip, String nama, Callback callback){

        loginDosenInterface.loginDosen(nip, nama).enqueue(callback);

    }
}
