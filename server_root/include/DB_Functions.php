<?php

class DB_Functions {

    private $conn;
    // constructor
    function __construct() {
        require_once 'include/DB_Connect.php';
        // connecting to database
        $db = new DB_Connect();
        $this->conn = $db->connect();
    }

    // destructor
    function __destruct() {

    }

    public function addUserImage($regno, $image) {

        $sql = "UPDATE users_student SET image='{$image}' WHERE regno={$regno}";
        if( !($this->conn->query($sql)) )
            return 'Error in Query' . $this->conn->error ;
        else
            return 'Upload successful';
    }

    /**
     * Storing new user
     * @param regno, password, name, fathername, gender, email, phoneno, dob, address, bloodgroup, hostel, roomno
     * returns boolean for successful addition
     */
    public function addUser($regno, $password, $name, $fathername, $gender, $email, $phoneno, $dob, $address, $bloodgroup, $hostel, $roomno) {

        $hash = $this->hashSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt

        $stmt = $this->conn->prepare("INSERT INTO users_student(regno, encrypted_password, salt, name, fathername, gender, email, phoneno, dob, address, bloodgroup, hostel, roomno, created_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
        $stmt->bind_param("issssssissssi", $regno, $encrypted_password, $salt, $name, $fathername, $gender, $email, $phoneno, $dob, $address, $bloodgroup, $hostel, $roomno);
        $result = $stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    public function hashSHA($password) {

        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }

    /**
     * Check for valid password
     * @param salt, password hash sent
     * returns encrypted_pass
     */
    public function checkPassHash($salt, $hash) {
        $checkEncyptPass = base64_encode($hash . $salt);
        return $checkEncyptPass;
    }

    /**
     * Fetching user details
     * @param regno
     * returns user details
     * *1 *2
     */

     public function getUserDetails($regno) {
        $query = "SELECT * FROM users_student WHERE regno = {$regno} LIMIT 1";
        $result = $this->conn->query($query);
        if(($result->num_rows) > 0) {
            $user = $result->fetch_assoc();
            $result->close();
            return $user;
        }
        else {
            return NULL;
        }
    }

    /*
    public function getUserDetails($regno) {
        $stmt = $this->conn->prepare("SELECT * FROM users_student WHERE regno = ? LIMIT 1");
        $stmt->bind_param("i",$regno);
        if($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        }
        else {
            return NULL;
        }
    }  */

    /**
     * Check user is existed or not
     * @param regno
     * returns boolean for existence
     */
    public function isUserExisted($regno) {
        $stmt = $this->conn->prepare("SELECT regno from users_student WHERE regno = ? LIMIT 1");
        $stmt->bind_param("i", $regno);
        $stmt->execute();
        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // user existed
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }

     /**
     * Storing new grievance
     * @param authority, subject, regno , type, grievance, status, created_at
     * returns boolean for successful addition
     */
    public function addGreviance( $authority, $subject, $regno , $type, $grievance, $status, $created_at ) {

        $stmt = $this->conn->prepare("INSERT INTO cms_student(authority, subject, regno, type, grievance, status, created_at) VALUES(?, ?, ?, ?, ?, 0, NOW())");
        $stmt->bind_param("ssiss", $authority, $subject, $regno, $type, $grievance);
        $result = $stmt->execute();
        $stmt->close();
        // check for successful store
        if ($result) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fetching grievance details from cms_student table
     * @param regno
     * returns all greviances of a user
     */

     public function getGreviances($regno) {
        $query = "SELECT * FROM cms_student WHERE regno = {$regno}";
        $result = $this->conn->query($query);
        if(($result->num_rows) > 0) {
            $user = $result->fetch_assoc();
            $result->close();
            return $user;
        }
        else {
            return NULL;
        }
    }
}

?>
