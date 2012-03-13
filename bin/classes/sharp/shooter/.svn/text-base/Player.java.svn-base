package sharp.shooter;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Player {

	private String number;
	private float orientation;
	private double longitude;
	private double latitude;
	
	private static Player instance;

	private Player( String number ) {
		this.number = number;
		this.longitude = 0.0;
		this.latitude = 0.0;
	}
	
	public static Player instance() {
		if( instance == null ) {
	    	TelephonyManager phoneManager = (TelephonyManager) GameUI.ctx.getSystemService( Context.TELEPHONY_SERVICE ); 
	    	String hisNumber = phoneManager.getLine1Number();
			instance = new Player( hisNumber );
		}
		return instance;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber( String number ) {
		this.number = number;
	}

	public void setLatitude( double latitude ) {
		this.latitude = latitude;
	}

	public void setLongitude( double longitude ) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}	
	
	public float getOrientation() {
		return orientation;
	}
	
	public void setOrientation( float orientation ) {
		this.orientation = orientation;
	}
	
	

}
