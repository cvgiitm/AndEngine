package com.andengine;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;

import android.util.Log;

public class Main extends BaseGameActivity implements IScrollDetectorListener, IOnSceneTouchListener {
	
	static final int CAMERA_WIDTH=480;
	static final int CAMERA_HEIGHT=320;
	
	private static final String TAG = "AndEngineTest";
	
	private ZoomCamera mCamera;
	private Texture mTexture;
	private TextureRegion mFaceTextureRegion;
	private SurfaceScrollDetector mScrollDetector;
	
    public void onLoadComplete(){
    	// TODO Auto-generated method stub
    }
    
    public Engine onLoadEngine(){
    	this.mCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
    	final int alturaTotal = CAMERA_HEIGHT*3;
    	this.mCamera.setBounds(0, CAMERA_WIDTH, 0, alturaTotal);
    	this.mCamera.setBoundsEnabled(true);
    	return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
    }
    
    public void onLoadResources(){
    	this.mTexture = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
    	this.mFaceTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "gfx/ui_ball_1.png", 0, 0);
    	this.mEngine.getTextureManager().loadTexture(this.mTexture);
    }
    
    public Scene onLoadScene(){
    	this.mEngine.registerUpdateHandler(new FPSLogger());
    	final Scene scene = new Scene(1);
    	scene.setBackground(new ColorBackground(0, 0, 0.3f));
    	scene.setOnAreaTouchTraversalFrontToBack();
    	final int centerX = (CAMERA_WIDTH - this.mFaceTextureRegion.getWidth()) / 2;
    	final int centerY = (CAMERA_HEIGHT - this.mFaceTextureRegion.getHeight()) / 2;
    	
    	this.mScrollDetector = new SurfaceScrollDetector(this);
    	this.mScrollDetector.setEnabled(true);
    	
    	final Sprite ball = new Sprite(centerX, centerY, this.mFaceTextureRegion);
    	scene.getLastChild().attachChild(ball);
    	
    	scene.setOnSceneTouchListener(this);
    	scene.setTouchAreaBindingEnabled(true);
    	
    	return scene;
    }    
    
    public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
    float pDistanceX, float pDistanceY) {
    	this.mCamera.offsetCenter(-pDistanceX, -pDistanceY);  
    	}
    		 
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
    	this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
    	return true;
    	}
}