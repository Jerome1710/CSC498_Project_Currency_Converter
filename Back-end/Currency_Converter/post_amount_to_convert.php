<?php

include('ConnecttoDb\my_db.php'); //
include('get_updated_rate.php'); //

$lbp = intval($_POST["lbp"]); // user input
$usd = intval($_POST["usd"]); // user input

if($lbp == 0){ // convert to lbp

	$jo = json_decode($json_buy_sell);
	$buy = intval($jo->buy_rate); 

	$conversion = $usd * $buy; // multiply the usd amount with the buy rate

	// add to the lbp_conversions table the usd amount that the user wants to convert in lbp and the converted amount in lbp
	$query = $mysqli->prepare("INSERT INTO lbp_conversions (amount_to_convert_usd, converted_amount_lbp) VALUES (?, ?)");
	$query->bind_param("ss", $usd, $conversion);
	$query->execute();

}
else if($usd == 0){ // convert to usd

	$jo = json_decode($json_buy_sell);
	$sell = intval($jo->sell_rate); 

	$conversion = $lbp * $sell; // multiply the usd amount with the buy rate

	// add to the lbp_conversions table the lbp amount that the user wants to convert in usd and the converted amount in usd
	$query = $mysqli->prepare("INSERT INTO usd_conversions (amount_to_convert_lbp, converted_amount_usd) VALUES (?, ?)");
	$query->bind_param("ss", $lbp, $conversion);
	$query->execute();

}

?>