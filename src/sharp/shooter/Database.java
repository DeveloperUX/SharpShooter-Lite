package sharp.shooter;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Database {
	
	public HttpClient httpClient;
	
	//public String scriptURL;
	private final static String TAG = "Database";
	private ArrayList<Player> Enemies;
    //HttpPost httppost = new HttpPost( scriptURL );

	public Database() {
		httpClient = new DefaultHttpClient();
	}
	
	public ArrayList<String> loadPlayers( ArrayList<String> phoneNumbersList ) {
		// List that will hold the return ArrayList
		ArrayList<String> loadedPlayers = new ArrayList<String>();
		// Create the List that will house the phone numbers and ids
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    //add numbers 
	    for( int i = 0; i < phoneNumbersList.size(); i++ ) { /** Note List might be empty if contact list is empty */
	    	nameValuePairs.add( new BasicNameValuePair( "id" + i, phoneNumbersList.get( i ) ) );
	    }
	    // Send list of String phone numbers to Server
	    String jsonResponse = sendToServer( nameValuePairs, "LoadPlayers.php" ); /** Note jsonResponse might be empty! */
		/* Attempt to parse the response from the server */
		try {
			// Convert Json formatted String to an Array
			JSONObject jObject = new JSONObject( jsonResponse );
			// iterate through list and extract numbers
			for( int jIndex = 0; jIndex < jObject.length(); jIndex++ ) {
				// create a Json object
				//JSONObject jObject = jsonArray.getJSONObject( jIndex );
				// extract data and place in list
				loadedPlayers.add( jObject.getString( "id" + jIndex ) );
			}		
		} catch( JSONException e ) {	
	            Log.e( TAG, "Error parsing data " + e.toString() );	
	    }
		// return list of numbers
		return loadedPlayers;
	}
	
	private String sendToServer( ArrayList<NameValuePair> players, String scriptName ) {
		
		//will hold the final result
	    String result = "";	    
	    // Attempt to contact server
	    try{ 
	    	// Set the URL to the specific script we want to run
	        HttpPost httppost = new HttpPost( GameEngine.SITE + scriptName );
	        // Encode the List and insert it into the Post object
            httppost.setEntity( new UrlEncodedFormEntity( players ) );
            // prepare the handler that will handle the response
            ResponseHandler<String> handler = new BasicResponseHandler(); 
            // Execute post and get the result
            result = httpClient.execute( httppost, handler );
            
	    } catch(Exception e) {
	    	Log.e( TAG, "Error in http connection "+e.toString());
	    }
	    //FEEDBACK FOR DEBUGGING
	    Log.e( GameEngine.TAG, "RESULT = " + result );
	    // return the final JSON result
	    return result;
	}
	
	public void sendLocation( String number, String status, double longitude, double latitude ) {
		//send this player's information to the server		
	}
	
	/*
	public void post() {
    	
	    String result = "";	
	    //the year data to send	
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    //add value
	    nameValuePairs.add(new BasicNameValuePair("year","Timmy XYZ"));
	    
	    InputStream is = null;
	    
	    //http post	    
	    try{
	        HttpPost httppost = new HttpPost( GameEngine.SITE );
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> handler = new BasicResponseHandler(); //test code
            result = httpClient.execute( httppost, handler ); //result used to be HttpResponse response
            //HttpEntity entity = response.getEntity();
            //is = entity.getContent();
	    } catch(Exception e) {
	    	Log.e( GameEngine.TAG, "Error in http connection "+e.toString());
	    }
	    //parse json data	
	    try{
	    	Log.e( GameEngine.TAG, "result = " + result + "END OF FILE" );
	    	JSONArray jArray = new JSONArray(result);
	
            for( int i=0; i<jArray.length(); i++ ) {	
            	JSONObject json_data = jArray.getJSONObject(i);	
                Log.e( GameEngine.TAG,"id: "+json_data.getInt("id") +
                    ", name: "+json_data.getString("name") +
                    ", email: "+json_data.getString("email") +	
                    ", opinion: "+json_data.getString("opinion")
                    );	
	            }
	    } catch( JSONException e ) {	
	            Log.e( GameEngine.TAG, "Error parsing data "+e.toString());	
	    }
    }
    */
}
