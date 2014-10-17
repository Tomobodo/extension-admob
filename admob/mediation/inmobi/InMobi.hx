package admob.mediation.inmobi;

import openfl.utils.JNI;

/**
 * ...
 * @author Thomas B
 */
class InMobi
{
	
	#if android
	static var initInMobi : Dynamic = JNI.createStaticMethod("org.haxe.extension.Admob", "initInMobi", "(Ljava/lang/String;)V");
	#end

	public static function initialize(property_id : String) {
		initInMobi(property_id);
	}
	
}