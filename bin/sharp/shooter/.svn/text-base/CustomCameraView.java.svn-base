package sharp.shooter;

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomCameraView extends SurfaceView {
	
	Camera camera;
	SurfaceHolder previewHolder;
   
	public CustomCameraView(Context ctx) {
				   	   
		super(ctx);	      
		previewHolder = this.getHolder();
		previewHolder.setType( SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS );	      
		
		SurfaceHolder.Callback surfaceHolderListener = new SurfaceHolder.Callback() {
		   
		      public void surfaceCreated(SurfaceHolder holder) {
		            camera = Camera.open();
		            try {
		                camera.setPreviewDisplay(previewHolder);
		            }
		            	catch (Throwable x ){ }
		           }
		      
		      public void surfaceChanged(SurfaceHolder holder, int format, int width,
		         int height) {
		    	  Parameters params = camera.getParameters();
		    	  params.setPreviewSize( width, height );
		    	  params.setPictureFormat(PixelFormat.JPEG);
		          camera.setParameters(params);
		          camera.startPreview();
		      }

		      public void surfaceDestroyed(SurfaceHolder arg0) {
		    	  camera.stopPreview();
		    	  camera.release();   
		      }
		 };
		 
		 previewHolder.addCallback(surfaceHolderListener);			 
		 
	}
   
}