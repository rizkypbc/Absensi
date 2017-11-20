 <?php

include_once 'connect.php';

$response = array();


if (isset($_GET['nama_dosen'])) {

  $nama_dosen = htmlspecialchars($_GET['nama_dosen']);


  $sql = $MySQLiconn->query("SELECT * FROM sesi WHERE nama_dosen = '$nama_dosen'");

  // $result = array();
  if (mysqli_num_rows($sql) > 0) {

    while ($row = $sql->fetch_array()) {

      // $response["error"] = FALSE;
      array_push($response, array('id_sesi'=>$row[0], 'materi'=>$row[1], 'nama_dosen'=>$row[2], 'pertemuan'=>$row[3], 'ruang'=>$row[4], 'tanggal'=>$row[5], 'waktu'=>$row[6], 'keterangan'=>$row[7], 'kode_matkul'=>$row[8]));
      // $response["message"] = "Data ada";
      // $response["sesiData"]["id_sesi"]=$row['id_sesi'];
      // $response["sesiData"]["materi"] = $row['materi'];
      // $response["sesiData"]["nama_dosen"] = $row['nama_dosen'];
      // $response["sesiData"]["pertemuan"] = $row['pertemuan'];
      // $response["sesiData"]["ruang"] = $row['ruang'];
      // $response["sesiData"]["tanggal"] = $row['tanggal'];
      // $response["sesiData"]["waktu"] = $row['waktu'];
      // $response["sesiData"]["keterangan"] = $row['keterangan'];
      // $response["sesiData"]["kode_matkul"] = $row['kode_matkul'];
    }

    echo json_encode($response);
  // } else {

  //   $response["error"] = TRUE;
  //   $response["message"] = "NIP atau Password Salah!";

  //   echo json_encode($response);
  // }
}



?>