package com.har.asyncvolleylib;

import android.os.Bundle;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

/**
 * Created by hareesh on 7/10/15.
 */
public class AsyncTaskRequest<T> extends Request<T> {

  private final int taskID;
  private AsyncListener<T> mListener;
  private Bundle bundle;

  public AsyncTaskRequest(Response.ErrorListener errListener) {
    super(Method.DEPRECATED_GET_OR_POST, "", errListener);
    this.taskID = -1;
  }

  public AsyncTaskRequest(int taskID, Response.ErrorListener errListener) {
    super(Method.DEPRECATED_GET_OR_POST, "", errListener);
    this.taskID = taskID;
  }

  public AsyncTaskRequest(int taskID, AsyncListener<T> listener,
                          Response.ErrorListener errListener) {
    super(Method.DEPRECATED_GET_OR_POST, "", errListener);
    this.taskID = taskID;
    this.mListener = listener;
  }

  @Override
  protected Response<T> parseNetworkResponse(NetworkResponse response) {
    if (response instanceof AsyncTaskNetworkResponse) {
      VolleyLog.v("Response in %d millis", response.networkTimeMs);
      return Response.success((T) ((AsyncTaskNetworkResponse) response).mObjectData, null);
    }
    return Response.error(new AsyncVolleyError(getTaskID(), "Parsing Response Failed"));
  }

  protected T performRequest() throws Exception {
    if (mListener != null)
      return mListener.performRequest(taskID, this);
    return null;
  }

  @Override
  protected void deliverResponse(T response) {
    if (mListener != null)
      mListener.onResponse(taskID, response);
  }

  public AsyncTaskRequest setAsyncListener(AsyncListener<T> mListener) {
    this.mListener = mListener;
    return this;
  }

  public Bundle getBundle() {
    return bundle;
  }

  public AsyncTaskRequest setBundle(Bundle bundle) {
    this.bundle = bundle;
    return this;
  }

  public int getTaskID() {
    return taskID;
  }

  /**
   * Callback interface for delivering parsed responses.
   */
  public interface AsyncListener<T> {
    /**
     * Called when a response is received.
     */
    public void onResponse(int taskID, T response);

    /**
     * Called to perform actual background task
     *
     * @param request actual request which started this task
     * @return result from the long running task
     */
    public T performRequest(int taskID, AsyncTaskRequest<T> request) throws Exception;

  }
}
