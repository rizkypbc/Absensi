<?php

include_once 'connect.php';

$response = array("error" => FALSE);


if (isset($_POST['nip']) && isset($_POST['password'])) {

	$nip = htmlspecialchars($_POST['nip']);
	$password = htmlspecialchars($_POST['password']);



	$sql = $MySQLiconn->query("SELECT * FROM dosen WHERE nip = '$nip' AND password = '$password'");

	if (mysqli_num_rows($sql) > 0) {

		while ($row = $sql->fetch_array()) {

			$response["error"] = FALSE;
			$response["message"] = "Login Berhasil";
			$response["dosenData"]["nip"]=$row['nip'];
			$response["dosenData"]["nama"] = $row['nama'];
			$response["dosenData"]["email"] = $row['email'];
			$response["dosenData"]["jurusan"] = $row['jurusan'];
			$response["dosenData"]["prodi"] = $row['prodi'];
		}

		echo json_encode($response);
	} else {

		$response["error"] = TRUE;
		$response["message"] = "NIP atau Password Salah!";

		echo json_encode($response);
	}
}



?>