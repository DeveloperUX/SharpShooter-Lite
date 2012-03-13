<?php
	/* declare some relevant variables */
	$DBhost = "localhost";
	$DBuser = "masonsc1_user";
	$DBpass = "Passw0rd";
	$DBName = "masonsc1_db";
	/* Connect to the database */
	mysql_connect($DBhost, $DBuser, $DBpass) or die("Unable to connect to database");
	mysql_select_db("$DBName") or die("Unable to select database $DBName"); 
	/* Decode the Json String into an array */
	$playersFound = array();
	$id = 0;
	foreach( $_REQUEST as $phoneNum ) {
		$playersFound['id'.$id] = $phoneNum;
		$id++;
//		$ret = $ret . $phoneNum;
	}
	
	echo json_encode( $playersFound );
	
	//$y = 38.6613 - 38.6607;
	//$x = -77.3098 - (-77.3099);
	//$m = $y/$x;
	//echo $m;
	
	//for( $i = 0; $i < $REQUEST
	//$name = $REQUEST['year'];
		
?>