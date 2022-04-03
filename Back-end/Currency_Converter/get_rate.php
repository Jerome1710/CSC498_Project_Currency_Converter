<?php

include('ConnecttoDb\my_db.php');

$query = $mysqli->prepare("SELECT count(id) FROM rates;");
$query->execute();
$result = $query->get_result();
$row = mysqli_fetch_row($result);
$counter = $row[0]; 


$query = $mysqli->prepare("SELECT buy_rate, sell_rate FROM rates WHERE id = $counter;");
$query->execute();
$result = $query->get_result();

$buy_sell = [];

while($rates = $result->fetch_assoc()){
    $buy_sell[] = $rates;
}

$json_buy_sell = json_encode($buy_sell[0]);
echo $json_buy_sell;



?>



