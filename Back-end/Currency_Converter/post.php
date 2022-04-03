<?php
include("db.php");

$id = $_GET["id"];
$lbp = $_GET["buy_rate"];
$usd = $_GET["sell_rate"];

$query = $mysqli -> prepare("INSERT INTO 'rates" ('id', 'buy_rate', 'sell_rate') VALUES (2, 3, 4);");
$query -> bind_param("idd",$id, $lbp, $usd);
$query -> execute();
?>