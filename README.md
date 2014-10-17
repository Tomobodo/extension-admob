Google AdMob extension for OpenFL applications. Working on both Android and iOS. Code is heavily based on NMEX extension which is pretty much outdated right now.

**Instructions**

1. Clone this repo using ```git clone admob https://github.com/TBaudon/admob-openfl``` command
2. Use ```haxelib dev admob-openfl path/to/your/clone``` command to register the lib
3. Include extension to your project ```<haxelib name="admob-openfl" /> ```
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

You'll need to include [googleplay-openfl](https://github.com/TBaudon/googleplay-openfl) haxelib in your project BEFORE admob-openfl.

**Prerequisites on iOS**

1. Add following code to your project.xml 
```xml
<ios linker-flags="-force_load __PATH_TO_THE_HAXELIB_DIR__/admob/git/ndll/iPhone/libGoogleAdMobAds.a" />

```
don't forget to replace ```__PATH_TO_THE_HAXELIB_DIR__``` with real path to your haxelib, in most cases it will be ```/usr/lib/haxe/lib```

2. After creating XCode project, drag libGoogleAdMobAds.a to Project Settings->Build Phases->Link with binaries
3. (Optional) You can rebuild binaries on Mac OSX using five simple commands:
```
cd __PATH_TO_THE_HAXELIB_DIR__/admob/git/project
haxelib run hxcpp Build.xml -Diphoneos
haxelib run hxcpp Build.xml -Diphoneos -DHXCPP_ARMV7
haxelib run hxcpp Build.xml -Diphonesim
haxelib run hxcpp Build.xml
```
