<?php

include_once 'connect.php';

$response = array("error" => FALSE);

if (isset($_POST['id_sesi']) && isset($_POST['materi']) && isset($_POST['keterangan'])) {

	$id_sesi = htmlspecialchars($_POST['id_sesi']);
	$materi = htmlspecialchars($_POST['materi']);
	$keterangan = htmlspecialchars($_POST['keterangan']);

	$sql = $MySQLiconn->query("UPDATE sesi SET materi = '$materi', keterangan = '$keterangan' WHERE id_sesi = '$id_sesi'");

	if ($sql) {

		$response["error"] = FALSE;
		$response["message"] = "Tutup Sesi Berhasil";

		echo json_encode($response);
	} else{

		$response["error"] = TRUE;
		$response["message"] = "Tutup Sesi Gagal";

		echo json_encode($response);
	}
}



?>