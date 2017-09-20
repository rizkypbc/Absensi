package skripsi.rizky.absensi.network.config;

/**
 * Created by ASUS on 12/07/2017.
 */

public class Config {

//    public static final String BASE_URL = "http://192.168.1.14";

    public static final String BASE_URL = "http://aksesblk-samarinda.com";

    public static final String API_URL = BASE_URL + "/absensi";

    public static final String API_LOGIN_MAHASISWA = API_URL + "/loginMahasiswa.php";

    public static final String API_REGISTER_MAHASISWA = API_URL + "/registerMahasiswa.php";

    public static final String API_LOGIN_DOSEN = API_URL + "/loginDosen.php";

    public static final String API_REGISTER_DOSEN = API_URL + "/registerDosen.php";

    public static final String API_IZIN = API_URL + "/izinMahasiswa.php";

    public static final String API_GET_LOCATION = API_URL + "/getLocation.php";

    public static final String API_BUKA_SESI = API_URL + "/bukaSesi.php";

    public static final String API_TUTUP_SESI = API_URL + "/UpdateSesi.php";

    public static final String API_LIAT_SESI = API_URL + "/liatSesi.php";

    //    public static final String DATA_URL_ABSEN = "http://192.168.1.14/absensi/MataKuliah/jumlahAbsen.php";
    public static final String KEY_NAME = "total";
    public static final String JSON_ARRAY = "result";

    //    public static final String DATA_URL_SAKIT = "http://192.168.1.14/absensi/MataKuliah/jumlahIzin.php";
    public static final String KEY_NAME_SAKIT = "sakit";
    public static final String JSON_ARRAY_SAKIT = "hasil";

}
