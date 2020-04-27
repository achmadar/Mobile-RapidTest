<?php 

include("../config.php");
	
	$id = $_POST['id'];
	$nama_rs = $_POST['nama_rs'];
	$alamat_rs = $_POST['alamat_rs'];
	$notelp_rs = $_POST['notelp_rs'];
	$jadwal_rs = $_POST['jadwal_rs'];

	$sql = "UPDATE tb_rumahsakit SET nama_rs='$nama_rs', alamat_rs='$alamat_rs', notelp_rs='$notelp_rs', jadwal_rs='$jadwal_rs' WHERE id = '$id' ";
	$query = mysqli_query($db, $sql);

	//apakah query update berhasil?
	if ($query) {
		
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
 ?>