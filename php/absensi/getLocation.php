<?php

include_once "connect.php";

$response = array("error" => FALSE);

if (isset($_POST['nim']) && isset($_POST['nama']) && isset($_POST['jurusan']) && isset($_POST['latitude']) && isset($_POST['longitude']) && isset($_POST['id_sesi'])) {

	$nim = htmlspecialchars($_POST['nim']);
	$nama = htmlspecialchars($_POST['nama']);
	$jurusan = htmlspecialchars($_POST['jurusan']);
	$latitude = htmlspecialchars($_POST['latitude']);
	$longitude = htmlspecialchars($_POST['longitude']);
	$id_sesi = htmlspecialchars($_POST['id_sesi']);
	$tanggal_absen = date("Y-m-d"); 
	$waktu_absen = time("H:i:s");

	$sql = $MySQLiconn->query("INSERT INTO absen(nim, nama, jurusan, latitude, longitude, tanggal_absen, waktu_absen, keterangan_absen, id_sesi, token_absen) VALUES ('$nim', '$nama', '$jurusan', '$latitude', '$longitude', '$tanggal_absen', now(), 'Hadir' ,'$id_sesi', 'Valid')");

	if ($sql) {
		
		$response["error"] = FALSE;
		$response["message"] = "Absen Berhasil";

		echo json_encode($response);
	} else {

		$response["error"] = TRUE;
		$response["message"] = "Absen Gagal";

		echo json_encode($response);
	}

}

?>