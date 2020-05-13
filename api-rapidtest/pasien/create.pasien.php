<?php 

include("../config.php");

	$username = $_POST['username'];
	$password = $_POST['password'];
	$konfirm_pass = $_POST['konfirm_pass'];
	$nama_user = $_POST['nama_user'];
	$alamat = $_POST['alamat'];
	$nohp_user = $_POST['nohp_user'];
	$umur = $_POST['umur'];
	$jeniskelamin = $_POST['jeniskelamin'];
	$goldarah = $_POST['goldarah'];
	$ttl_pasien = $_POST['ttl_pasien'];
	$keterangan = $_POST['keterangan'];

	$sql = "INSERT INTO tb_pasien(username, password, konfirm_pass, nama_user, alamat, nohp_user, umur, jeniskelamin, goldarah, ttl_pasien, keterangan) VALUES('$username', '$password', '$konfirm_pass', '$nama_user', '$alamat', '$nohp_user', '$umur', '$jeniskelamin', '$goldarah', '$ttl_pasien', '$keterangan')";
	$query = mysqli_query($db, $sql);

	//apakah query update berhasil?
	if ($query) {
		
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
 ?>