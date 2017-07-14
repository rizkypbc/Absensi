package skripsi.rizky.absensi.network.config;

/**
 * Created by ASUS on 12/07/2017.
 */

public class Config {

    public static final String BASE_URL = "http://192.168.1.8";

    public static final String API_URL = BASE_URL + "/absensi";

    public static final String API_LOGIN_MAHASISWA = API_URL + "/loginMahasiswa.php";

    public static final String API_REGISTER_MAHASISWA = API_URL + "/registerMahasiswa.php";

    public static final String API_LOGIN_DOSEN = API_URL + "/loginDosen.php";

    public static final String API_REGISTER_DOSEN = API_URL + "/registerDosen.php";

}
