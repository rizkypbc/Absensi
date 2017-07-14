package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 12/07/2017.
 */

public interface RegisterMahasiswaInterface {

    @FormUrlEncoded
    @POST(Config.API_REGISTER_MAHASISWA)
    Call<BaseResponse> registerMahasiswa(
            @Field("nim") String nim,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("jurusan") String jurusan,
            @Field("prodi") String prodi
    );
}
