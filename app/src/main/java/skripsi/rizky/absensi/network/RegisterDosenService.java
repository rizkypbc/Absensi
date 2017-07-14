package skripsi.rizky.absensi.network;

import android.content.Context;

import retrofit2.Callback;
import skripsi.rizky.absensi.network.config.RetrofitBuilder;
import skripsi.rizky.absensi.network.interfaces.RegisterDosenInterface;

/**
 * Created by ASUS on 13/07/2017.
 */

public class RegisterDosenService {

    private RegisterDosenInterface registerDosenInterface;

    public RegisterDosenService(Context context){

        registerDosenInterface = RetrofitBuilder.builder(context)
                .create(RegisterDosenInterface.class);
    }

    public void doRegisterDosen(String nip, String nama, String password, String email, String jurusan, String prodi, Callback callback){

        registerDosenInterface.registerDosen(nip, nama, password, email, jurusan, prodi).enqueue(callback);

    }
}
