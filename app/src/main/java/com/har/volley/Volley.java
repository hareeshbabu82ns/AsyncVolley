package com.har.volley;

import android.content.Context;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;

import java.io.File;

/**
 * Created by hareesh on 7/10/15.
 */
public class Volley {
  private static final String DEFAULT_CACHE_DIR = "volley";

  private static Context mApplicationContext;
  private static RequestQueue networkQueue = null;
  private static RequestQueue asyncQueue = null;

  public static synchronized RequestQueue getNetworkQueue() {
    if (networkQueue == null)
      throw new IllegalStateException("Queue not initialized");
    return asyncQueue;
  }

  public static synchronized RequestQueue getNetworkQueue(Context context) {
    if (networkQueue == null)
      networkQueue = com.android.volley.toolbox.Volley.newRequestQueue(context
          .getApplicationContext());
    return networkQueue;
  }

  public static synchronized RequestQueue getAsyncQueue() {
    if (asyncQueue == null)
      throw new IllegalStateException("AsyncQueue not initialized");
    return asyncQueue;
  }

  public static synchronized RequestQueue getAsyncQueue(Context context) {
    if (asyncQueue == null)
      asyncQueue = newAsyncRequestQueue(context);
    return asyncQueue;
  }

  public static RequestQueue newAsyncRequestQueue(Context context) {
    mApplicationContext = context.getApplicationContext();
    final File cacheDir = new File(mApplicationContext.getCacheDir(), DEFAULT_CACHE_DIR);
    final Network network = new AsyncTaskNetwork();
    final RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
    queue.start();
    return queue;
  }
}
