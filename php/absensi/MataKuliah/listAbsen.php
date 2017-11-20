<?php

include_once("connection.php");
// $query = "SELECT pid, name, qty, price, image_url
// 		 FROM tbl_product ORDER BY pid DESC";

// $query = "SELECT * FROM absen";

$kode_matkul = $_GET['kode_matkul'];
$pertemuan = $_GET['pertemuan'];

// $query = "SELECT nim, nama, jurusan, tanggal_absen, waktu_absen, nama_dosen, pertemuan, kode_matkul from absen INNER JOIN sesi USING (id_sesi) where kode_matkul ='$kode_matkul'";

$query = "SELECT nim, nama, jurusan, tanggal_absen, waktu_absen, nama_dosen, pertemuan, kode_matkul, keterangan_absen from absen INNER JOIN sesi USING (id_sesi)  where kode_matkul ='$kode_matkul' AND pertemuan = '$pertemuan' AND token_absen = 'Valid' ORDER BY waktu_absen DESC";


$result = mysqli_query($conn, $query);

while ($row = mysqli_fetch_assoc($result)) {
	
	$data[] = $row;
}

echo json_encode($data);
?>