var httpObject;

function loadPlayers() {
	/* Get Json string */
	var jsonString = field.value;
	// Create Request Object
	httpObject = new XMLHttpRequest();	
	// Create Response Handler
	httpObject.onreadystatechange = printResult;
	// Send request to server
	httpObject.open( "POST", "ParseTest.php", true );
	httpObject.send( "jsonString=" + jsonString );
}

function printResult() {
	// Check return status
	if( httpObject.readyState == 4 ) {
		// Get returned string
		var retString = httpObject.reponseText;
		// Display returned string
		document.getElementById('result').innerHTML = retString;
	}
	else {
		document.getElementById('status').innerHTML = 'ReadyState: ' + httpObject.readyState + '; Status: ' + httpObject.status;
	}
}