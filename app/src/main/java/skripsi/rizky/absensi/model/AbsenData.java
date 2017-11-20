package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 19/08/2017.
 */

public class AbsenData {

    String id;
    String nim;
    String nama;
    String jurusan;
    String kelas;
    double latitude;
    double longitude;
    String tanggal_absen;
    String waktu_absen;
    String id_sesi;
    String kode_matkul;
    String keterangan_absen;

    public AbsenData() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTanggal_absen() {
        return tanggal_absen;
    }

    public void setTanggal_absen(String tanggal_absen) {
        this.tanggal_absen = tanggal_absen;
    }

    public String getWaktu_absen() {
        return waktu_absen;
    }

    public void setWaktu_absen(String waktu_absen) {
        this.waktu_absen = waktu_absen;
    }

    public String getId_sesi() {
        return id_sesi;
    }

    public void setId_sesi(String id_sesi) {
        this.id_sesi = id_sesi;
    }

    public String getKode_matkul() {
        return kode_matkul;
    }

    public void setKode_matkul(String kode_matkul) {
        this.kode_matkul = kode_matkul;
    }

    public String getKeterangan_absen() {
        return keterangan_absen;
    }

    public void setKeterangan_absen(String keterangan_absen) {
        this.keterangan_absen = keterangan_absen;
    }
}
