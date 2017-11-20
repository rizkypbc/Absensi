<?php

include_once("connection.php");
// $query = "SELECT pid, name, qty, price, image_url
// 		 FROM tbl_product ORDER BY pid DESC";

$query = "SELECT * FROM ruang";

$result = mysqli_query($conn, $query);

while ($row = mysqli_fetch_assoc($result)) {
	
	$data[] = $row;
}

echo json_encode($data);
?>