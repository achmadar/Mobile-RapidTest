<?php 

include("../config.php");
	
	$id = $_POST['id'];
	$nama_pasien = $_POST['nama_pasien'];
	$nama_rs = $_POST['nama_rs'];
	$jadwaltest = $_POST['jadwaltest'];
	$status = $_POST['status'];
	$keterangan = $_POST['keterangan'];

	$sql = "UPDATE tb_report SET nama_pasien='$nama_pasien', nama_rs='$nama_rs', jadwaltest='$jadwaltest', status='$status', keterangan='$keterangan' WHERE id = '$id' ";
	$query = mysqli_query($db, $sql);

	//apakah query update berhasil?
	if ($query) {
		
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
 ?>