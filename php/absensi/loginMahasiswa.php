<?php

include_once 'connect.php';

$response = array("error" => FALSE);


if (isset($_POST['nim']) && isset($_POST['password'])) {

	$nim = htmlspecialchars($_POST['nim']);
	$password = htmlspecialchars($_POST['password']);


	$sql = $MySQLiconn->query("SELECT * FROM mahasiswa WHERE nim = '$nim' AND password = '$password'");

	if (mysqli_num_rows($sql) > 0) {

		while ($row = $sql->fetch_array()) {

			$response["error"] = FALSE;
			$response["message"] = "Login Berhasil";
			$response["mahasiswaData"]["nim"]=$row['nim'];
			$response["mahasiswaData"]["nama"] = $row['nama'];
			// $response["mahasiswaData"]["email"] = $row['email'];
			// $response["mahasiswaData"]["jurusan"] = $row['jurusan'];
			// $response["mahasiswaData"]["prodi"] = $row['prodi'];
		}

		echo json_encode($response);
	} else {

		$response["error"] = TRUE;
		$response["message"] = "NIM atau Password Salah!";

		echo json_encode($response);
	}
}



?>