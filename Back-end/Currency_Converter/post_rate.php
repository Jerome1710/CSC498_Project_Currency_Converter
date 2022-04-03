<?php

include('ConnecttoDb\my_db.php');
include('buy.php'); // Get updated rate of buy
include('sell.php'); // Get updated rate of sell 


// Inserting updated rate in the rates table  
$stmt = $mysqli->prepare("INSERT INTO rates (buy_rate, sell_rate) VALUES (?, ?)");
$stmt->bind_param("ii", $updated_buy, $updated_sell);
$stmt->execute();
$stmt->store_result();

?>
