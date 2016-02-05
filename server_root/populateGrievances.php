<?php
	require_once 'include/DB_Functions.php';
	$db = new DB_Functions();
	// json response array
	$response = array("error" => 0);

	$regno = $_POST['regno'];
	$authority = $_POST['authority'];
	$type = $_POST['type'];
	$subject = $_POST['subject'];
	$grievance = $_POST['grievance'];

	$response = $db->addGreviance( $authority, $subject, $regno , $type, $grievance );
	if($response)
		echo 'Success';
	else
		echo 'Unsuccessfull';

?>
