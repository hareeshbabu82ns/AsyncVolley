package com.har.volley;

import android.os.SystemClock;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;

/**
 * Created by hareesh on 7/10/15.
 */
public class AsyncTaskNetwork extends BasicNetwork {
  //no HttpStack is required as this is AsyncTask running locally or using ProtoBuf API
  public AsyncTaskNetwork() {
    super(null);
  }

  @Override
  public NetworkResponse performRequest(Request<?> request) throws VolleyError {
    if (request instanceof AsyncTaskRequest) {
      long requestStart = SystemClock.elapsedRealtime();
      try {
        AsyncTaskRequest<?> asyncTaskReq = (AsyncTaskRequest<?>) request;
        final Object responseData = asyncTaskReq.performRequest();
        return new AsyncTaskNetworkResponse(responseData,
            SystemClock.elapsedRealtime() - requestStart);
      } catch (Exception ex) {
        throw new VolleyError(ex);
      }
    } else
      return super.performRequest(request);
  }
}
