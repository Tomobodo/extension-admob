=================================================================

Ad Network Adapter for Google AdMob Ads Mediation SDK for Android

=================================================================


This is an adapter to be used in conjunction with the Google 
Play Services Mediation SDK for Android.

Requirements:

  - A Mediation ID
- Android SDK 2.1 or later
  
- InMobi Android SDK 4.5.1



Instructions:


  - Add the included jar into your Android project with its associated Ad Network SDK.

  - Enable the Ad network in the Ad Network Mediation UI.

  - Make ad requests normally using the AdMob SDK using the mediation 
	ID for the placement rather than the publisher ID.
  
- Hardware acceleration can be disabled using public static method "disableHardwareAcceleration" in InMobiAdapter  
  - To set test mode , use Inmobi's Test mode diagnostics
"http://www.inmobi.com/helpcenter/publisher-help/integration/how-to-set-up-publisher-diagnostics/"



The latest documentation and code samples for the AdMob SDK are 
available at:

https://developers.google.com/mobile-ads-sdk/docs/

