<?php

include_once("connection.php");
// $query = "SELECT pid, name, qty, price, image_url
// 		 FROM tbl_product ORDER BY pid DESC";

// $query = "SELECT * FROM sesi ORDER BY id_sesi DESC";
$query = "SELECT * FROM sesi WHERE keterangan = 'Dibuka' and token_sesi = 'Valid' ORDER BY id_sesi DESC";


$result = mysqli_query($conn, $query);

while ($row = mysqli_fetch_assoc($result)) {
	
	$data[] = $row;
}

echo json_encode($data);
?>