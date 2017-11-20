<?php

include_once("connection.php");
// $query = "SELECT pid, name, qty, price, image_url
// 		 FROM tbl_product ORDER BY pid DESC";

// $query = "SELECT * FROM absen WHERE token_absen = 'Invalid' ORDER BY id_sesi DESC";

$query = "SELECT id, nim, nama, jurusan, tanggal_absen, waktu_absen, nama_dosen, kode_matkul, keterangan_absen, token_absen from absen INNER JOIN sesi USING (id_sesi) WHERE token_absen = 'Invalid'";


$result = mysqli_query($conn, $query);

while ($row = mysqli_fetch_assoc($result)) {
	
	$data[] = $row;
}

echo json_encode($data);
?>