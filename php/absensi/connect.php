<?php

define('HOST_NAME', 'localhost');
define('DATABASE_NAME', 'absensi');
define('DATABASE_USER_NAME', 'root');
define('DATABASE_PASSWORD', '');

$MySQLiconn = new MySQLi(HOST_NAME, DATABASE_USER_NAME, DATABASE_PASSWORD, DATABASE_NAME);

if ($MySQLiconn->connect_errno) {

	die("ERROR: -> ".$MySQLiconn->connect_error);
}

?>