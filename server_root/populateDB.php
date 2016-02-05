<!DOCTYPE html>
<html>
	<head><title>File Insert</title></head>
	<body>
		<h3>Please Choose a File and click Submit</h3>

		<form enctype="multipart/form-data" action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">
		<input type="hidden" name="MAX_FILE_SIZE" value="16777216" />
		<input type="file" name="userfile" />
		<input type="submit" value="Submit" />
	</form>

	<?php
		require_once 'include/DB_Functions.php';
		$db = new DB_Functions();
		$regno = 20129011;
		$password = 'depp1993';
		$name = 'Deepankar Kumar Singh';
		$fathername = 'Pradeep Kumar Singh';
		$gender = 'M';
		$email = 'singh.deepnakar93@gmail.com';
		$phoneno = 9670977885;
		$dob = '1993-03-05';
		$address = '498/64 - B, Vivekanand Puri , Lucknow - 226007';
		$bloodgroup = 'O+';
		$hostel = 'Tilak';
		$roomno = '13';

		if($db->addUser($regno, $password, $name, $fathername, $gender, $email, $phoneno, $dob, $address, $bloodgroup, $hostel, $roomno)) {
			 $user  = $db->getUserDetails($regno);
			 echo '<br/>' . $user['name'] . ' successfully stored in the db';
		}

		// check if a file was submitted
		if(!isset($_FILES['userfile']))
		{
	    	echo '<p>Please select a file</p>';
		}
		else
		{
	    	try {
		    	$msg= upload();  //this will upload your image
		    	echo $msg;  //Message showing success or failure.
		    }
		    catch(Exception $e) {
		    	echo $e->getMessage();
		    	echo 'Sorry, could not upload file';
		    }
		}

		// the upload function

		function upload() {
			global $db;
			global $regno;


		    $maxsize = 16777216; //set to approx 16 MB

		    //check associated error code
		    if($_FILES['userfile']['error']==UPLOAD_ERR_OK) {

		        //check whether file is uploaded with HTTP POST
		        if(is_uploaded_file($_FILES['userfile']['tmp_name'])) {

		            //checks size of uploaded image on server side
		            if( $_FILES['userfile']['size'] < $maxsize) {

		               	//checks whether uploaded file is of image type
		              	//if(strpos(mime_content_type($_FILES['userfile']['tmp_name']),"image")===0) {
		                $finfo = finfo_open(FILEINFO_MIME_TYPE);
		                if(strpos(finfo_file($finfo, $_FILES['userfile']['tmp_name']),"image")===0) {

		                    // prepare the image for insertion
		                    $imgData = addslashes(file_get_contents($_FILES['userfile']['tmp_name']));

		                    // put the image in the db...
		                    $msg = '<p> ' . $db->addUserImage($regno,$imgData) . '</p>';

		                }
		                else
		                    $msg="<p>Uploaded file is not an image.</p>";
		            }
		             else {
		                // if the file is not less than the maximum allowed, print an error
		                $msg='<div>File exceeds the Maximum File limit</div>
		                <div>Maximum File limit is '.$maxsize.' bytes</div>
		                <div>File '.$_FILES['userfile']['name'].' is '.$_FILES['userfile']['size'].
		                ' bytes</div><hr />';
		                }
		        }
		        else
		            $msg="File not uploaded successfully.";

		    }
		    else {
		        $msg= file_upload_error_message($_FILES['userfile']['error']);
		    }
		    return $msg;
		}

		// Function to return error message based on error code

		function file_upload_error_message($error_code) {
		    switch ($error_code) {
		        case UPLOAD_ERR_INI_SIZE:
		            return 'The uploaded file exceeds the upload_max_filesize directive in php.ini';
		        case UPLOAD_ERR_FORM_SIZE:
		            return 'The uploaded file exceeds the MAX_FILE_SIZE directive that was specified in the HTML form';
		        case UPLOAD_ERR_PARTIAL:
		            return 'The uploaded file was only partially uploaded';
		        case UPLOAD_ERR_NO_FILE:
		            return 'No file was uploaded';
		        case UPLOAD_ERR_NO_TMP_DIR:
		            return 'Missing a temporary folder';
		        case UPLOAD_ERR_CANT_WRITE:
		            return 'Failed to write file to disk';
		        case UPLOAD_ERR_EXTENSION:
		            return 'File upload stopped by extension';
		        default:
		            return 'Unknown upload error';
		    }
		}


	?>
</body>
</html>
