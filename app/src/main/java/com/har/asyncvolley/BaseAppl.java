package com.har.asyncvolley;

import android.app.Application;

import com.har.volley.Volley;

/**
 * Created by hareesh on 7/10/15.
 */
public class BaseAppl extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    //initialize Async Queue
    Volley.getAsyncQueue(this);
  }
}
