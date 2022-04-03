<?php

include('ConnecttoDb\my_db.php');
include('get_updated_rate.php');

$lbp = intval($_POST["lbp"]);
$usd = intval($_POST["usd"]);

if($lbp == 0){ // convert to lbp

	$jo = json_decode($json_buy_sell);
	$buy = intval($jo->buy_rate); 

	$conversion = $usd * $buy;

	$query = $mysqli->prepare("INSERT INTO lbp_conversions (amount_to_convert_usd, converted_amount_lbp) VALUES (?, ?)");
	$query->bind_param("ss", $usd, $conversion);
	$query->execute();

}
else if($usd == 0){ // convert to usd

	$jo = json_decode($json_buy_sell);
	$sell = intval($jo->sell_rate); 

	$conversion = $lbp * $sell;

	$query = $mysqli->prepare("INSERT INTO usd_conversions (amount_to_convert_lbp, converted_amount_usd) VALUES (?, ?)");
	$query->bind_param("ss", $lbp, $conversion);
	$query->execute();

}

?>