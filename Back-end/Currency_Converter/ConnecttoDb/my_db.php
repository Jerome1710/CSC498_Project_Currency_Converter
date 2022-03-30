<?php

$db_host = "localhost"; 
$db_user = "root"; 
$db_password = null; 
$db_name = "currencydb"; 


$mysqli = new mysqli($db_host, $db_user, $db_password, $db_name); 

if(mysqli_connect_errno()){
	die("Connection failed!");
}


?>