<?php

class DB_Connect
{
    private $conn;
    // Connecting to database
    function __construct () {
         require_once 'include/Config.php';
         // Connecting to mysql database
         $this->conn = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
    }

    // return database handler
    public function connect() {
        return $this->conn;
    }
}

?>