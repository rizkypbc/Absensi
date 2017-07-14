package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 12/07/2017.
 */

public class Mahasiswa extends BaseResponse {

    private MahasiswaData mahasiswaData;

    public Mahasiswa(){

    }

    public MahasiswaData getMahasiswaData() {
        return mahasiswaData;
    }

    public void setMahasiswaData(MahasiswaData mahasiswaData) {
        this.mahasiswaData = mahasiswaData;
    }


}
