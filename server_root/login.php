<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => 0);

if (isset($_POST['regno']) && isset($_POST['passHash'])) {
 
    // receiving the post params
    $regno = $_POST['regno'];
    $passHash = $_POST['passHash'];
 
    // get the user details
    $user = $db->getUserDetails($regno);

    if ($user != NULL) {
        // user is found , now check for the correct login credentials 
        $salt = $user["salt"];
        if ( $db->checkPassHash( $salt, $passHash )  == $user["encrypted_password"]) {
            $response["error"] = 0;
            $response["user"]["name"] = $user["name"];
            $response["user"]["regno"] = $user["regno"];
            $response["user"]["fathername"] = $user["fathername"];
            $response["user"]["gender"] = $user["gender"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["phoneno"] = $user["phoneno"];
            $response["user"]["dob"] = $user["dob"];
            $response["user"]["address"] = $user["address"];
            $response["user"]["bloodgroup"] = $user["bloodgroup"];
            $response["user"]["hostel"] = $user["hostel"];
            $response["user"]["roomno"] = $user["roomno"];
            $response["user"]["image"] = base64_encode($user["image"]);
            echo json_encode($response);
        }
        else {
            $passHashRecv = $db->checkPassHash( $salt, $passHash );
            // wrong login credentials
            $response["error"] = 1;
            $response["error_msg"] = "Login credentials wrong.";
            $response["passHashRecv"] = $passHashRecv;
            echo json_encode($response);
        }
       
    } else {
        // user is not found
        $response["error"] = 2;
        $response["error_msg"] = "Registration No doesn't exists.";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = 3;
    $response["error_msg"] = "Required parameters regno or password is missing!";
    echo json_encode($response);
}
?>