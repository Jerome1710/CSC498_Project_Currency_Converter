<?php

include('ConnecttoDb\my_db.php');
include('buy.php');
include('sell.php');

$stmt = $mysqli->prepare("INSERT INTO rates (buy_rate, sell_rate) VALUES (?, ?)");
$stmt->bind_param("ii", $updated_buy, $updated_sell);
$stmt->execute();
$stmt->store_result();

?>
