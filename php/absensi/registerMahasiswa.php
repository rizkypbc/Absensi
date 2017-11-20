<?php

include_once 'connect.php';

$response = array("error" => FALSE);

if (isset($_POST['nim']) && isset($_POST['nama']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['jurusan']) && isset($_POST['prodi'])) {

	$nim = htmlspecialchars($_POST['nim']);
	$nama = htmlspecialchars($_POST['nama']);
	$email = htmlspecialchars($_POST['email']);
	$password = htmlspecialchars($_POST['password']);
	$jurusan = htmlspecialchars($_POST['jurusan']);
	$prodi = htmlspecialchars($_POST['prodi']);

	$encrypted_password = hash('sha256', $password);

	$sql = $MySQLiconn->query("SELECT nim from mahasiswa WHERE nim = '$nim'");

	if (mysqli_num_rows($sql) > 0) {

		$response["error"] = TRUE;
		$response["message"] = "NIM telah terdaftar";

		echo json_encode($response);
	} else {

		$sql = $MySQLiconn->query("INSERT INTO mahasiswa(nim, nama, email, password, jurusan, prodi) VALUES ('$nim', '$nama', '$email', '$encrypted_password', '$jurusan', '$prodi')");

	if ($sql) {

		$response["error"] = FALSE;
		$response["message"] = "Register Succesfull";

		echo json_encode($response);
	} else{

		$response["error"] = TRUE;
		$response["message"] = "Register Failure";

		echo json_encode($response);
	}

	}

}

?>