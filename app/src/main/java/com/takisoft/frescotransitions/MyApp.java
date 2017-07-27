package com.takisoft.frescotransitions;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient client = new OkHttpClient.Builder()
                //.followSslRedirects(true)
                //.connectionSpecs(Collections.singletonList(ConnectionSpec.MODERN_TLS))
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        ImagePipelineConfig pipelineConfig = OkHttpImagePipelineConfigFactory.newBuilder(this, client)
                .setDownsampleEnabled(true)
                .build();

        Fresco.initialize(this, pipelineConfig);
    }
}
