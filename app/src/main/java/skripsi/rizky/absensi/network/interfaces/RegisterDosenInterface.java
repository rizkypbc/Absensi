package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 13/07/2017.
 */

public interface RegisterDosenInterface {

    @FormUrlEncoded
    @POST(Config.API_REGISTER_DOSEN)
    Call<BaseResponse> registerDosen(
            @Field("nip") String nip,
            @Field("nama") String nama,
            @Field("password") String password,
            @Field("email") String email,
            @Field("jurusan") String jurusan,
            @Field("prodi") String prodi
    );
}
