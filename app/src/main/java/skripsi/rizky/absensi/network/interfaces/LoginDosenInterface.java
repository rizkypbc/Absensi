package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.Dosen;
import skripsi.rizky.absensi.model.Mahasiswa;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 13/07/2017.
 */

public interface LoginDosenInterface {


    @FormUrlEncoded
    @POST(Config.API_LOGIN_DOSEN)
    Call<Dosen> loginDosen(
            @Field("nip") String nip,
            @Field("password") String password
    );

}
