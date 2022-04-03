<?php

include('ConnecttoDb\post_rate.php'); // will call the post_rate method

// select the last element in the table
$query = $mysqli->prepare("SELECT count(id) FROM rates;");
$query->execute();
$result = $query->get_result();
$row = mysqli_fetch_row($result);
$counter = $row[0]; 

// select the latest buy rate and sell rate from the table
$query = $mysqli->prepare("SELECT buy_rate, sell_rate FROM rates WHERE id = $counter;");
$query->execute();
$result = $query->get_result();

// 
$buy_sell = [];

// 
while($rates = $result->fetch_assoc()){
    $buy_sell[] = $rates;
}
// converting to a JSON object 
$json_buy_sell = json_encode($buy_sell[0]);
echo $json_buy_sell;



?>



