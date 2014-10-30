#ifndef STATIC_LINK
#define IMPLEMENT_API
#endif

#if defined(HX_WINDOWS) || defined(HX_MACOS) || defined(HX_LINUX)
#define NEKO_COMPATIBLE
#endif

#include <hx/CFFI.h>

#include "AD.h"

using namespace admobIOS;


extern "C" void admob_main() {
	val_int(0); // Fix Neko init
}
DEFINE_ENTRY_POINT(admob_main);

extern "C" int admob_register_prims() {
	return 0;
}

#ifdef IPHONE

AutoGCRoot *eval_onAdLoaded = 0;
AutoGCRoot *eval_onAdFailed = 0;
AutoGCRoot *eval_onAdClosed = 0;

extern "C" {
    void onAdLoaded(){
        if(eval_onAdLoaded != 0)
            val_call0(eval_onAdLoaded->get());
    }

    void onAdFailed(int errorCode){
        if(eval_onAdFailed != 0)
            val_call1(eval_onAdFailed->get(), alloc_int(errorCode));
    }

    void onAdClosed(){
        if(eval_onAdClosed != 0)
            val_call0(eval_onAdClosed->get());
    }
}

value admob_ad_init(value id, value x, value y, value size, value testMode) {
	initAd(val_string(id), val_int(x), val_int(y), val_int(size), val_bool(testMode));
	return alloc_null();
}
DEFINE_PRIM(admob_ad_init, 5);

value admob_ad_show() {
	showAd();
	return alloc_null();
}
DEFINE_PRIM(admob_ad_show, 0);

value admob_ad_hide() {
	hideAd();
	return alloc_null();
}
DEFINE_PRIM(admob_ad_hide,0);

value admob_ad_refresh() {
	refreshAd();
	return alloc_null();
}
DEFINE_PRIM(admob_ad_refresh,0);

value admob_ad_init_interstitial(value id, value testMode) {
	initInterstitial(val_string(id), val_bool(testMode));
	return alloc_null();
}
DEFINE_PRIM(admob_ad_init_interstitial, 2);

value admob_ad_show_interstitial() {
	showInterstitial();
	return alloc_null();
}
DEFINE_PRIM(admob_ad_show_interstitial, 0);

void admob_ad_set_interstitial_listeners(value onAdLoaded, value onAdFailed, value onAdClosed){
    if(onAdLoaded != NULL)
        eval_onAdLoaded = new AutoGCRoot(onAdLoaded);
    if(onAdFailed != NULL)
        eval_onAdFailed = new AutoGCRoot(onAdFailed);
    if(onAdClosed != NULL)
        eval_onAdClosed = new AutoGCRoot(onAdClosed);
}
DEFINE_PRIM(admob_ad_set_interstitial_listeners, 3);
#endif
