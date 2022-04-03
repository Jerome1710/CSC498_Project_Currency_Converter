<?php

include('ConnecttoDb\my_db.php');
include('post_amount.php');


if($lbp == 0){ // convert to lbp

    $query = $mysqli->prepare("SELECT count(id) FROM lbp_conversions;");
    $query->execute();
    $result = $query->get_result();
    $row = mysqli_fetch_row($result);
    $counter = $row[0]; 


    $query = $mysqli->prepare("SELECT converted_amount_lbp  FROM lbp_conversions WHERE id = $counter;");
    $query->execute();
    $result = $query->get_result();

    $converted = [];

    while($lbp_conversions = $result->fetch_assoc()){
        $converted[] = $lbp_conversions;
    }

    $json_converted = json_encode($converted[0]);
    echo $json_converted;


}
else if($usd == 0){ // convert to usd

    $query = $mysqli->prepare("SELECT count(id) FROM usd_conversions;");
    $query->execute();
    $result = $query->get_result();
    $row = mysqli_fetch_row($result);
    $counter = $row[0]; 


    $query = $mysqli->prepare("SELECT converted_amount_usd  FROM usd_conversions WHERE id = $counter;");
    $query->execute();
    $result = $query->get_result();

    $converted = [];

    while($usd_conversions = $result->fetch_assoc()){
        $converted[] = $usd_conversions;
    }

    $json_converted = json_encode($converted[0]);
    echo $json_converted;

}

?>