package com.picker;

import android.app.Application;

import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;

/**
 * Created by Amit Patoliya on 8/3/18.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RxPaparazzo.register(this).withFileProviderPath(".basecode");
    }
}
