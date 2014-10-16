package org.haxe.extension;

import java.security.MessageDigest;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

////////////////////////////////////////////////////////////////////////
import android.webkit.WebView.FindListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.graphics.Color;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.AdRequest.Builder;
////////////////////////////////////////////////////////////////////////

/* 
	You can use the Android Extension class in order to hook
	into the Android activity lifecycle. This is not required
	for standard Java code, this is designed for when you need
	deeper integration.
	
	You can access additional references from the Extension class,
	depending on your needs:
	
	- Extension.assetManager (android.content.res.AssetManager)
	- Extension.callbackHandler (android.os.Handler)
	- Extension.mainActivity (android.app.Activity)
	- Extension.mainContext (android.content.Context)
	- Extension.mainView (android.view.View)
	
	You can also make references to static or instance methods
	and properties on Java classes. These classes can be included 
	as single files using <java path="to/File.java" /> within your
	project, or use the full Android Library Project format (such
	as this example) in order to include your own AndroidManifest
	data, additional dependencies, etc.
	
	These are also optional, though this example shows a static
	function for performing a single task, like returning a value
	back to Haxe from Java.
*/
public class Admob extends Extension {
	
	
	////////////////////////////////////////////////////////////////////////
	static RelativeLayout adLayout;
	static RelativeLayout.LayoutParams adMobLayoutParams;
	static AdView adView;
	static Boolean adVisible = false, adInitialized = false, adTestMode = false;
	static InterstitialAd interstitial;
	static String deviceHash;
	////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode 
	 * you started it with, the resultCode it returned, and any additional data 
	 * from it.
	 */
	public boolean onActivityResult (int requestCode, int resultCode, Intent data) {
		return true;
	}
	
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {
		Log.i("trace", "OOOOOOOH");
		
		Log.i("trace","CREATE");
		adLayout = new RelativeLayout(mainContext);
		adMobLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
		mainActivity.addContentView(adLayout, adMobLayoutParams);
	}
	
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
		
		
		
	}
	
	
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {
		
		if (adView != null) {
			adView.pause();
		}		
		
		
	}
	
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
		if (adView != null) {
			adView.resume();
		}		
	}
	
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
		
		
		
	}
	
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {
		
		
		
	}
	
	public void onSaveInstanceState (Bundle outState) {

	}

	/**
	 * Called after onStart() when the activity is being re-initialized from 
	 * a previously saved state.
	 */
	public void onRestoreInstanceState (Bundle savedState) {

	}
	
	////////////////////////////////////////////////////////////////////////
	static public void loadAd() {
		try {
			AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
			
			if(adTestMode){	
				adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
				adRequestBuilder.addTestDevice(deviceHash);
			}
			
			AdRequest adRequest = adRequestBuilder.build();
			Log.i("trace", "IS TEST : " + adRequest.isTestDevice(mainContext));
		
			adView.loadAd(adRequest);
		}catch(Exception e){
			Log.i("trace","Error loadAd: " + e.getMessage());
		}
	}
	
	static public void initAd(final String id, final int x, final int y, final boolean testMode) {
		
		mainActivity.runOnUiThread(new Runnable() {
			public void run() {
				
				String adID = id;
				adTestMode = testMode;
				
				if (mainActivity == null) {
					return;
				}

				adView = new AdView(mainActivity);
				adView.setAdUnitId(id);
				adView.setAdSize(AdSize.SMART_BANNER);
				loadAd();

				adMobLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
       
                if(x == 0) {
					adMobLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                }
				else if(x == 1) {
					adMobLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                }
				else if(x == 2) {
					adMobLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                }
				
				if(y == 0) {
					adMobLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				}
				else if(y == 1) {
					adMobLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				}
				else if(y == 2) {
					adMobLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                }
				
				adInitialized = true;
			}
		});
	}
	
	static public void showAd() {
		mainActivity.runOnUiThread(new Runnable() {
			public void run() {
				try {
					if (adInitialized && !adVisible) {
						adLayout.removeAllViews();
						adView.setBackgroundColor(Color.BLACK);
						adLayout.addView(adView, adMobLayoutParams);
						adView.setBackgroundColor(0);
						adVisible = true;
					}
				} catch (Exception e){
					if(adView == null)
						Log.i("trace","adView null");
					if(adLayout == null) 
						Log.i("trace","adLayout null");
					if(adMobLayoutParams == null)
						Log.i("trace","admoblayoutparam null");
					Log.i("trace","Error showAd: " + e.getMessage());
				}
			}
		});
	}
        
	static public void hideAd() {
		mainActivity.runOnUiThread(new Runnable() {
			public void run() {
				if (adInitialized && adVisible) {
					adLayout.removeAllViews();
					loadAd();
					adVisible = false;
				}
			}
		});
	}
	
	static public void loadInterstitial() {
		AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
		if(adTestMode){	
			adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
			adRequestBuilder.addTestDevice(deviceHash);
		}
		AdRequest adRequest = adRequestBuilder.build();
		interstitial.loadAd(adRequest);
	}
	
	static public void initInterstitial(final String id, final boolean testMode) {
        mainActivity.runOnUiThread(new Runnable() {
            public void run() {
            	adTestMode = testMode;
				if (Extension.mainActivity == null) {
					return;
				}
				
                interstitial = new InterstitialAd(mainActivity);
                interstitial.setAdUnitId(id);

                loadInterstitial();
            }
        });
    }

    static public void showInterstitial() {
        mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                if (interstitial.isLoaded()) {
                    interstitial.show();
                }
            }
        });
    }
    
    static public void setTestDevice(String hash) {
    	deviceHash = hash;
    }
	///////////////////////////////////////////////////////////////////////////////////////////	
}