<?php 

		
		require_once('dbConnect.php');
		
		$kode_matkul = $_GET['kode_matkul'];
		$pertemuan = $_GET['pertemuan'];
		
		$sql = "SELECT COUNT(*) as sakit from absen INNER JOIN sesi USING (id_sesi) where kode_matkul ='$kode_matkul' AND pertemuan = '$pertemuan' AND keterangan_absen = 'Sakit' AND token_absen = 'Valid'";
		
		$r = mysqli_query($con,$sql);
		
		$res = mysqli_fetch_array($r);
		
		$result = array();
		
		array_push($result,array(
			"sakit"=>$res['sakit']
			)
		);
		
		echo json_encode(array("hasil"=>$result));
		
		mysqli_close($con);
		


