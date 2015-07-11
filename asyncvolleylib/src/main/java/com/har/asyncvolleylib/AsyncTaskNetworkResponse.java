package com.har.asyncvolleylib;

import com.android.volley.NetworkResponse;

import org.apache.http.HttpStatus;

/**
 * Created by hareesh on 7/10/15.
 */
public class AsyncTaskNetworkResponse extends NetworkResponse {
  Object mObjectData;

  public AsyncTaskNetworkResponse(Object data) {
    this(data, 0);
  }

  public AsyncTaskNetworkResponse(Object data, long networkTimeMs) {
    super(HttpStatus.SC_OK, null, null, false, networkTimeMs);
    mObjectData = data;
  }
}
