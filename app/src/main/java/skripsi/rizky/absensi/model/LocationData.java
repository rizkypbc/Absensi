package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 04/08/2017.
 */

public class LocationData {

    private String nim;
    private String nama;
    private String jurusan;
    private String kelas;
    private double latitude;
    private double longitude;
    private String id_sesi;

    public LocationData() {

    }

    public String getId_sesi() {
        return id_sesi;
    }

    public void setId_sesi(String id_sesi) {
        this.id_sesi = id_sesi;
    }


    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
