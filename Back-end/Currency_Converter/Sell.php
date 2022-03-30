<?php
$url = 'https://lirarate.org/wp-json/lirarate/v2/rates?currency=LBP&_ver=t202233019';
$json = file_get_contents($url);
$jo = json_decode($json);
$length = count($jo->sell);
echo $jo->sell[$length - 1][1];
?>