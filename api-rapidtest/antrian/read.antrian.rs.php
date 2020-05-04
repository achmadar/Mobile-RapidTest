<?php

include("../config.php");

$sql = "SELECT COUNT(id), nama_rs FROM tb_antrian GROUP BY nama_rs";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array(
    'antrian' => $row['COUNT(id)'],
    'nama_rs' => $row['nama_rs'],
));
}
echo json_encode(array("result" => $result));
?>