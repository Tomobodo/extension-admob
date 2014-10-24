Google AdMob extension for OpenFL applications. 
Working on both Android and iOS (iOs is broken at the moment on this fork, will soon work on it). 
Code is heavily based on NMEX extension which is pretty much outdated right now.

**Instructions**

1. Clone this repo using ```git clone admob https://github.com/TBaudon/admob-openfl``` command
2. Use ```haxelib dev admob path/to/your/clone``` command to register the lib
3. Include extension to your project ```<haxelib name="admob" /> ```
4. Import AD class ```import admob.AD; ```
5. Setup ads at the beginning of your code ```AD.init(ADMOB_ID, AD.LEFT, AD.BOTTOM, AD.BANNER_PORTRAIT, false);```
where arguments are following:
  - Ad unit id.
  - Position in x axis. Could be ```AD.LEFT``` or ```AD.RIGHT```
  - Position in y axis. Could be ```AD.TOP``` or ```AD.BOTTOM```
  - Banner size. Could be 
      - ```AdSize.SMART_BANNER```
      - ```AdSize.BANNER``` 
      - ```AdSize.FULL_BANNER```
      - ```AdSize.LARGE_BANNER```
  - Test mode. Whether enable test ads or not. Default value is false.
6. Show banner ```AD.show(); ```
7. You can hide it anytime by calling ```AD.hide();```
8. Use AD.setTestDevice("YOUR_DEVICE_HASH") if you want to use test ad on android devices (plus setting testMode to true in init functions)
 
**Prerequisites on Android**

You'll need to include [google-play-services](https://github.com/TBaudon/google-play-services-openfl) haxelib in your project BEFORE admob.

**Prerequisites on iOS**

1. Build the ndll with the following command :
```
lime rebuild admob ios
```
5. That's it!
