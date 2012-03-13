package sharp.shooter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameUI extends Activity implements OnClickListener {
	
	private ProgressDialog progressBar;
	
	private GameEngine gameEngine;
	private Handler uiHandler;
	
	public static volatile Context ctx;
	public static volatile Activity ui;
	public final String TAG = "GameUI";
	
	/** Camera Variables */
	private boolean mPreviewRunning  = false;
	private Camera cam;
	private SurfaceHolder mSurfaceHolder;
	private SurfaceView mSurfaceView;
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        // Display the Content View
        super.onCreate(savedInstanceState);
		Log.e( TAG, "handleMessage(): Something is wrong." );
        setContentView( R.layout.camerasurface );

        // Save the context for easy access
        ctx = getApplicationContext();
        // Create Activity pointer for other classes
        ui = this;
        
        
        /** Text view for debugging */
        final TextView tv = (TextView) findViewById( R.id.tv1 );
        tv.setText( "Starting Test Application...\n");
        
        
        /**
         * Turn on the camera and display overlay
         */
    	mSurfaceView = (SurfaceView) findViewById(R.id.surface_camera);    	 
    	//we get the holder from the SurfaceView
    	mSurfaceHolder = mSurfaceView.getHolder();
    	mSurfaceHolder.setType( SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS );   
    	// Set the Surface's Call Back which takes care of Displaying the Camera and changing orientations
    	SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
    		public void surfaceCreated( SurfaceHolder holder ) {
    			
    			cam = Camera.open();

    			try {
    				cam.setPreviewDisplay( mSurfaceHolder );
    			}
    			catch (Throwable t) {
    				Log.e("PreviewDemo-surfaceCallback",
    							"Exception in setPreviewDisplay()", t);
    				Toast
    					.makeText( GameUI.this, t.getMessage(), Toast.LENGTH_LONG)
    					.show();
    			}
    		}
    		/** Do this to fix the camera stretching and when the user changes the orientation of the phone  */
    		public void surfaceChanged( SurfaceHolder holder, int format, int width, int height ) {
    			// Get the camera's current parameters
    			Camera.Parameters params = cam.getParameters();
    			// Set the parameters to those of the phone
    			params.setPreviewSize( width, height );
    			cam.setParameters( params );
    			// Start displaying the Camera feed
    			cam.startPreview();
    		}
    		/** When Camera is closed or application exits, stop Updating Camera Display and Release the Camera resource */
    		public void surfaceDestroyed(SurfaceHolder holder) {
    			// Stop the camera feed
    			cam.stopPreview();
    			cam.release();
    			cam = null;
    		}
    	};
		// The CallBack will be in charge of Setting Camera changes
    	mSurfaceHolder.addCallback( surfaceCallback );
    	/** 
         * Done with Camera 
         */
        //CustomCameraView cv = new CustomCameraView( this.getApplicationContext() );
    	/*
        LinearLayout layout = (LinearLayout) findViewById( R.id.layout );
        layout.removeView( tv );
        layout.addView( cv );
        */
        //FrameLayout rl = new FrameLayout( this.getApplicationContext() );
        //setContentView(rl);
        //rl.addView(cv);
        
        /*
        try {
        	cv.startActivity( Intent.getIntent( Intent.ACTION_CAMERA_BUTTON ) );        	
        } catch( URISyntaxException uriE ) {
        	tv.append( "Error: URI Exception..\n" );
        }
		*/
        // create the handler that will take care of messages from Game Thread
        uiHandler = new Handler() {
        	// handle the incoming messages
        	public void handleMessage( Message msg ) {
        		
        		if( msg.what == GameEngine.PLAYERSLOADED ) {
        			int x = (Integer) msg.obj;
        			Log.e( TAG, "handleMessage(): Message (" + x + ") Recieved from Game Engine." );
        			tv.append( "Number of Players Loaded: " + x + "\n" );
        		}
        		// on message "Done Loading" //display weapon image and unBlock onClick
        			// display something
        			else {
        				Log.e( TAG, "handleMessage(): Something is wrong." );
        				tv.append( "Error: Received wrong message..\n" );
        			}
        	}
        };
        
      
        // Setup the Game Thread with the given Handler
        GameEngine gameEngine = new GameEngine( uiHandler );  

        // Display Loading screen while Game is loading
         //progressBar.setProgressStyle( 1 );
         //progressBar = ProgressDialog.show( this, "Title", "Message" );         
         //progressBar.setMax( 100 );
         
        // Initiate the Game in the background 
        gameEngine.start();
        
        // Success?
        Log.e( TAG, "SUCCESS!!!" );        
        
    }
    
	public void onClick( View v ) {
		// notfiy Game Engine of User Click
		Log.e( TAG, "Notifying game Engine of User Click..." );
		
	}
		
	// Action to take when user clicks the home button (PAUSE)
	
	// Action to take when user restarts the application (RESUME)
	
}