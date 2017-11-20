<?php

include_once "connect.php";

$response = array("error" => FALSE);

// if (isset($_POST['id_sesi']) && isset($_POST['materi']) && isset($_POST['nama_dosen']) && isset($_POST['ruang']) && isset($_POST['keterangan']) && isset($_POST['kode_matkul'])) {

if (isset($_POST['pertemuan']) && isset($_POST['materi']) && isset($_POST['nama_dosen']) && isset($_POST['ruang']) && isset($_POST['keterangan']) && isset($_POST['kode_matkul'])) {

	// $id_sesi = htmlspecialchars($_POST['id_sesi']);
	$pertemuan = htmlspecialchars($_POST['pertemuan']);
	$materi = htmlspecialchars($_POST['materi']);
	$nama_dosen = htmlspecialchars($_POST['nama_dosen']);
	$ruang = htmlspecialchars($_POST['ruang']);
	$keterangan = htmlspecialchars($_POST['keterangan']);
	$kode_matkul = htmlspecialchars($_POST['kode_matkul']);
	$tanggal_absen = date("Y-m-d"); 
	$waktu_absen = time("H:i:s");
	// $token = htmlspecialchars($_POST['token']);

	$sql = $MySQLiconn->query("INSERT INTO sesi(materi, nama_dosen, pertemuan, ruang, tanggal, waktu, keterangan, kode_matkul, token_sesi) VALUES ('$materi', '$nama_dosen', '$pertemuan', '$ruang', now(), now(), '$keterangan', '$kode_matkul', 'Valid')");

	if ($sql) {
		
		$response["error"] = FALSE;
		$response["message"] = "Buka Sesi Berhasil";

		echo json_encode($response);
	} else {

		$response["error"] = TRUE;
		$response["message"] = "Buka Sesi Gagal";

		echo json_encode($response);
	}

}

?>