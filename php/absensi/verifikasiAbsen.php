<?php

include_once 'connect.php';

$response = array("error" => FALSE);

if (isset($_POST['id']) && isset($_POST['token_absen'])) {

	$id = htmlspecialchars($_POST['id']);
	$token_absen = htmlspecialchars($_POST['token_absen']);


	$sql = $MySQLiconn->query("UPDATE absen SET token_absen = '$token_absen' WHERE id = '$id'");

	if ($sql) {

		$response["error"] = FALSE;
		$response["message"] = "Verifikasi Berhasil";

		echo json_encode($response);
	} else{

		$response["error"] = TRUE;
		$response["message"] = "Verifikasi Gagal";

		echo json_encode($response);
	}
}



?>