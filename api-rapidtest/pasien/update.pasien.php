<?php 

include("../config.php");
	
	$username = $_POST['username'];
	$nama_user = $_POST['nama_user'];
	$alamat = $_POST['alamat'];
	$nohp_user = $_POST['nohp_user'];
	$umur = $_POST['umur'];
	$jeniskelamin = $_POST['jeniskelamin'];
	$goldarah = $_POST['goldarah'];
	$ttl_pasien = $_POST['ttl_pasien'];

	$sql = "UPDATE tb_pasien SET nama_user='$nama_user', alamat='$alamat', nohp_user='$nohp_user', umur='$umur', jeniskelamin='$jeniskelamin', goldarah='$goldarah', ttl_pasien='$ttl_pasien' WHERE username = '$username'";
	$query = mysqli_query($db, $sql);

	//apakah query update berhasil?
	if ($query) {
		
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
 ?>