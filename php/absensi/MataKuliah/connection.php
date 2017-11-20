<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "absensi";

// mysql_connect($servername, $username, $password) or die("Koneksi Gagal");
// msql_select_db($dbname) or die("Database tidak tersambung")
// $conn = mysqli_connect($servername, $username, $password, $dbname);

// if(!$conn){
// 	die("Connection failed: " . mysqli_connect_error());
// }
//  echo "koneksi berhasul";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>