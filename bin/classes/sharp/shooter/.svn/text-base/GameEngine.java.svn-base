package sharp.shooter;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;

public class GameEngine extends Thread {
	
	public static final String SITE = "http://masonscheduler.com/android/";
	
	private LocationManager myLocationManager;
	private SensorManager sensorManager;
	private SensorEventListener sensor;
	private Handler uiHandler;
	
	public Database db;
	private Player hero;
	private ArrayList<String> enemyNumbers;	
	
	public static final String TAG = "GameEngine";
	public static int PLAYERSLOADED = 1;
	
    /** Initialize Game variables */
    public GameEngine( Handler uiHandler ) {
        enemyNumbers = new ArrayList<String>(); // Contains a list of the Enemies
        db = new Database();  // Contains all access to Server
        hero = Player.instance();  // Create the main Player
        // set the Handler that will receive messages
        this.uiHandler = uiHandler;
    }
    
    /** Run the main game thread in background */
    public void run() {
    	Log.e( TAG, "Run(): Game STARTED" );
    	try {
    		// load the User's Contacts
    		loadContacts();
    		
    	} catch( Exception e ) {  // Problem loading Game notify UI
    		Log.e( TAG, "Run(): Game not loaded: " + e.getMessage() );
    	}
    	// Load was successful
    	Log.e( TAG, "Run(): Load was successful" );
		// Instantiate the Players
		int numOfPlayers = initPlayers();
		// Players received from Server
		Log.e( TAG, "Run(): " + numOfPlayers + " Players loaded." );
		// Notify the UI that Players were Loaded Successfully
		sendUIMessage( PLAYERSLOADED, numOfPlayers );
		
		/** Main Game loop */
		
    }
    
    private void sendUIMessage( int what, Object message ) {
    	Message msg = uiHandler.obtainMessage( what, message );
    	uiHandler.sendMessage( msg );
    }
    
    /** Load the Enemies */
    private void loadContacts() throws Exception { 
    	//query to get a cursor to the list of phones
    	String[] projection = new String[] { Phone.NUMBER };
    	//Cursor c = managedQuery( ContactsContract.Contacts.CONTENT_URI, columns, null, null, null );//ContactsContract.Contacts.CONTENT_URI, columns, null, null, null );
    	Cursor c = GameUI.ui.managedQuery( Phone.CONTENT_URI, projection, null, null, null );
    	//iterate and save the numbers to a list    	
    	int colIndex = c.getColumnIndex( Phone.NUMBER );
    	// Retrieve phone numbers from device		
		if( c.moveToFirst() ) {  //move Cursor to beginning of Table
			// loop Cursor through Data Table to retrieve phone numbers
			while( c.moveToNext() ) {
				// Store phone numbers to send to server
    			enemyNumbers.add( c.getString( colIndex ) );
			}
		}    		
		Log.e( TAG, "LoadGame(): Players have been loaded." );
		// Close the Cursor, not needed anymore
		c.close(); 		
    }
    
    /** Get Players from the server */
    private int initPlayers() {
    	//send list of phones to database and get Players
    	enemyNumbers = db.loadPlayers( enemyNumbers );
    	return enemyNumbers.size();
    }
    
    /*
    *//** Create the Listener to get orientation of the Device *//*
    public void loadListener() {
    	// Create the Sensor Manager
    	sensorManager = ( SensorManager ) GameUI.ctx.getSystemService( Context.SENSOR_SERVICE );
		Sensor defaultSensor = sensorManager.getDefaultSensor( Sensor.TYPE_ORIENTATION );
		// Register a listener
		sensorManager.registerListener( sensor,  defaultSensor, SensorManager.SENSOR_DELAY_UI );
		// Create the Listener
		sensor = new SensorEventListener() {
			// implement the requried methods
			public void onAccuracyChanged( Sensor sensor, int accuracy ) {} //not used
			// called when device is pointed in different direction
			public void onSensorChanged( SensorEvent event ) {
				Log.e( GameEngine.TAG, "Compass: " + event.values[0] );
				// set the player's view direction
				hero.setOrientation( event.values[0] );
			}
		};
    }
    */
    
/*        
        obtainLocation();
        //Database.connectTo( "http://masonscheduler.com/android/PostTest.php" );
        
        //get player's phone number
		TelephonyManager telephony = (TelephonyManager) getSystemService( Context.TELEPHONY_SERVICE ); 
		//make a new player with the found number
		Player me = new Player( telephony.getLine1Number() );
    }
    
    public void obtainLocation() {
	     Use the LocationManager class to obtain GPS locations 	
	    LocationManager mlocManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );	
	    Player mlocListener = new Player( "5715592323" );	
	    mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
    }
    */
    
    

}