package com.andengine;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.util.FPSLogger;

public class Main extends BaseGameActivity {
	
	static final int CAMERA_WIDTH=480;
	static final int CAMERA_HEIGHT=320;
	
	private static final String TAG = "AndEngineTest";
	
	private ZoomCamera mCamera;
	
    public void onLoadComplete(){
    	// TODO Auto-generated method stub
    }
    
    public Engine onLoadEngine(){
    	this.mCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
    	return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
    }
    
    public void onLoadResources(){
    	// TODO Auto-generated method stub
    }
    
    public Scene onLoadScene(){
    	this.mEngine.registerUpdateHandler(new FPSLogger());
    	@SuppressWarnings("deprecation")
		final Scene scene = new Scene(1);
    	scene.setBackground(new ColorBackground(0, 0, 0.8784f));
    	return scene;
    }    
}