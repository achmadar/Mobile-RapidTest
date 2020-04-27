<?php 

include("../config.php");
	$id = $_POST['id'];
	$name = $_POST['name'];
	$email = $_POST['email'];
	$password = $_POST['password'];

	$sql = "UPDATE users SET name='$name', email='$email', password='$password' WHERE id = '$id' ";
	$query = mysqli_query($db, $sql);

	//apakah query update berhasil?
	if ($query) {
		
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
 ?>