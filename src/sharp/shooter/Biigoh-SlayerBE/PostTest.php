<?php
	/* declare some relevant variables */
	$DBhost = "localhost";
	$DBuser = "masonsc1_user";
	$DBpass = "Passw0rd";
	$DBName = "masonsc1_db";
	
	mysql_connect($DBhost, $DBuser, $DBpass) or die("Unable to connect to database");
	mysql_select_db("$DBName") or die("Unable to select database $DBName"); 
				
	$name = $_REQUEST['year'];
				
	$query = mysql_query("SELECT * FROM info WHERE name='$name'");
	
	//print( $query ); /*DEBUGGING*/
	
	while( $e = mysql_fetch_assoc($query) )
	        $output[] = $e;
	        
	print(json_encode($output));

	mysql_close();
?>