<?php

include("../config.php");

$sql = "SELECT * FROM users";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('name' => $row['name'],
    'email' => $row['email']
));
}
echo json_encode(array("result" => $result));
?>



