<?php

include("../config.php");

$sql = "SELECT * FROM tb_rumahsakit";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('id' => $row['id'],
    'nama_rs' => $row['nama_rs'],
    'alamat_rs' => $row['alamat_rs'],
    'notelp_rs' => $row['notelp_rs'],
    'jadwal_rs' => $row['jadwal_rs']
));
}
echo json_encode(array("result" => $result));
?>



