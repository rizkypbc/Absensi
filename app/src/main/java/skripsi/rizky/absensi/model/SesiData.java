package skripsi.rizky.absensi.model;

import java.io.Serializable;

/**
 * Created by ASUS on 08/08/2017.
 */

public class SesiData implements Serializable {

    private String id_sesi;
    private String materi;
    private String nama_dosen;
    private String pertemuan;
    private String ruang;
    private String keterangan;
    private String kode_matkul;
    private String tanggal;
    private String waktu;

    public SesiData() {

    }

    public String getId_sesi() {
        return id_sesi;
    }

    public void setId_sesi(String id_sesi) {
        this.id_sesi = id_sesi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }

    public String getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(String pertemuan) {
        this.pertemuan = pertemuan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKode_matkul() {
        return kode_matkul;
    }

    public void setKode_matkul(String kode_matkul) {
        this.kode_matkul = kode_matkul;
    }

}
