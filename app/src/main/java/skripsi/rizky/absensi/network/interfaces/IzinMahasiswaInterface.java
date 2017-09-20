package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 18/09/2017.
 */

public interface IzinMahasiswaInterface {

    @FormUrlEncoded
    @POST(Config.API_IZIN)
    Call<BaseResponse> izinMahasiswa(
            @Field("nim") String nim,
            @Field("nama") String nama,
            @Field("jurusan") String jurusan,
            @Field("id_sesi") String id_sesi);
}
