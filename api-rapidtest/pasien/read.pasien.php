<?php

include("../config.php");

$username = $_POST['username'];
$password = $_POST['password'];

$sql = "SELECT id, username, password, nama_user, alamat, nohp_user, umur, jeniskelamin, goldarah, ttl_pasien, keterangan FROM tb_pasien WHERE username='$username' AND password='$password'";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('id' => $row['id'],
    'username' => $row['username'],
    'password' => $row['password'],
    'nama_user' => $row['nama_user'],
    'alamat' => $row['alamat'],
    'nohp_user' => $row['nohp_user'],
    'umur' => $row['umur'],
    'jeniskelamin' => $row['jeniskelamin'],
    'goldarah' => $row['goldarah'],
    'ttl_pasien' => $row['ttl_pasien'],
    'keterangan' => $row['keterangan']
));
}
echo json_encode(array("result" => $result));
?>



