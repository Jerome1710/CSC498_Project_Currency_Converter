<?php

$db_host = "localhost"; 
$db_user = "root"; 
$db_password = null; 
$db_name = "currencydb"; // name of the database


$mysqli = new mysqli($db_host, $db_user, $db_password, $db_name); // Creating an object of type mysqli and that is how to connect to the database

// Checking connection
if(mysqli_connect_errno()){
	die("Connection failed!");
}


?>