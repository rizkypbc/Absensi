	<?php

include_once 'connect.php';

$response = array("error" => FALSE);

if (isset($_POST['nip']) && isset($_POST['nama']) && isset($_POST['password']) && isset($_POST['email']) && isset($_POST['jurusan']) && isset($_POST['prodi'])) {

	$nip = htmlspecialchars($_POST['nip']);
	$nama = htmlspecialchars($_POST['nama']);
	$password = htmlspecialchars($_POST['password']);
	$email = htmlspecialchars($_POST['email']);
	$jurusan = htmlspecialchars($_POST['jurusan']);
	$prodi = htmlspecialchars($_POST['prodi']);

	$encrypted_password = hash('sha256', $password);

	$sql = $MySQLiconn->query("SELECT nip from dosen WHERE nip = '$nip'");

	if (mysqli_num_rows($sql) > 0) {

		$response["error"] = TRUE;
		$response["message"] = "NIP telah terdaftar";

		echo json_encode($response);
	} else {

		$sql = $MySQLiconn->query("INSERT INTO dosen(nip, nama, password, email, jurusan, prodi) VALUES ('$nip', '$nama', '$encrypted_password', '$email', '$jurusan', '$prodi')");

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