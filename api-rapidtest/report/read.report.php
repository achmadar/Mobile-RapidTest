<?php

include("../config.php");

$sql = "SELECT * FROM tb_report";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('id' => $row['id'],
    'nama_pasien' => $row['nama_pasien'],
    'nama_rs' => $row['nama_rs'],
    'jadwaltest' => $row['jadwaltest'],
    'status' => $row['status'],
    'keterangan' => $row['keterangan']
));
}
echo json_encode(array("result" => $result));
?>



