<?php 

include("../config.php");
	
	$id = $_POST['id'];
	$nama_pasien = $_POST['nama_pasien'];
	$nama_rs = $_POST['nama_rs'];
	$jadwalpilihan = $_POST['jadwalpilihan'];
	$keluhan = $_POST['keluhan'];
	$Status = $_POST['Status'];

	$sql = "UPDATE tb_antrian SET nama_pasien='$nama_pasien', nama_rs='$nama_rs', jadwalpilihan='$jadwalpilihan', keluhan='$keluhan', Status='$Status' WHERE id = '$id' ";
	$query = mysqli_query($db, $sql);

	//apakah query update berhasil?
	if ($query) {
		
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
 ?>