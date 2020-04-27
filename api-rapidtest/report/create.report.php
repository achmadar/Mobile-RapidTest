<?php 

include("../config.php");

	$id = $_POST['id'];
	$nama_pasien = $_POST['nama_pasien'];
	$nama_rs = $_POST['nama_rs'];
	$jadwaltest = $_POST['jadwaltest'];
	$status = $_POST['status'];
	$keterangan = $_POST['keterangan'];

	$sql = " INSERT INTO tb_report VALUES id = '$id', nama_pasien='$nama_pasien', nama_rs='$nama_rs', jadwaltest='$jadwaltest', status='$status', keterangan='$keterangan' ";
	$query = mysqli_query($db, $sql);

	//apakah query update berhasil?
	if ($query) {
		
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
 ?>