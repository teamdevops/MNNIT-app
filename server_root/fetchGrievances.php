<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => 0);


if (isset($_POST['regno'])) {

	$regno = $_POST['regno'];
    // get the greviance details
    $result = $db->getGreviances($regno);

    if ($result != NULL) {
       		 // user is found ,reponse got in the for of array , encoding to json
        
            $response["error"] = 0;
            $response["greviance"]["compid"] = $result["name"];
            $response["greviance"]["regno"] = $result["regno"];
            $response["greviance"]["subject"] = $result["subject"];
            $response["greviance"]["type"] = $result["type"];
            $response["greviance"]["grievance"] = $result["grievance"];
            $response["greviance"]["status"] = $result["status"];
            $response["greviance"]["created_at"] = $result["created_at"];
            echo json_encode($response);
        } else {
	        //no grievance found
	        $response["error"] = 1;
	        $response["error_msg"] = "No grievance found for this regno.";
	        echo json_encode($response);
    }
}
else {
    // required post params is missing
    $response["error"] = 3;
    $response["error_msg"] = "Required parameters regno is missing!";
    echo json_encode($response);
}
?>
