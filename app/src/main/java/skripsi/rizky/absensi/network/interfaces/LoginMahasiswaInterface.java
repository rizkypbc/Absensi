package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.Mahasiswa;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 12/07/2017.
 */

public interface LoginMahasiswaInterface {

    @FormUrlEncoded
    @POST(Config.API_LOGIN_MAHASISWA)
    Call<Mahasiswa> loginMahasiswa(
            @Field("nim") String nim,
            @Field("password") String password
    );
}
