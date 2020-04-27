<?php 

include("../config.php");
	
	$id = $_POST['id'];
	$username = $_POST['username'];
	$password = $_POST['password'];
	$nama_user = $_POST['nama_user'];
	$alamat = $_POST['alamat'];
	$nohp_user = $_POST['nohp_user'];
	$umur = $_POST['umur'];
	$jeniskelamin = $_POST['jeniskelamin'];
	$goldarah = $_POST['goldarah'];
	$ttl_pasien = $_POST['ttl_pasien'];
	$keterangan = $_POST['nohp_user'];

	$sql = "UPDATE tb_pasien SET username='$username', password='$password', nama_user='$nama_user', alamat='$alamat', nohp_user='$nohp_user', umur='$umur', jeniskelamin='$jeniskelamin', goldarah='$goldarah', ttl_pasien='$ttl_pasien', keterangan='$keterangan' WHERE id = '$id' ";
	$query = mysqli_query($db, $sql);

	//apakah query update berhasil?
	if ($query) {
		
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
 ?>