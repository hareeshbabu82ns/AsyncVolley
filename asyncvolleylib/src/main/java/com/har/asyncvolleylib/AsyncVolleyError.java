package com.har.asyncvolleylib;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/**
 * Created by hareesh on 21/07/15.
 */
public class AsyncVolleyError extends VolleyError {
  protected int mTaskID = 0;

  public AsyncVolleyError(int taskID) {
    super();
    mTaskID = taskID;
  }

  public AsyncVolleyError(int taskID, NetworkResponse response) {
    super(response);
    mTaskID = taskID;
  }

  public AsyncVolleyError(int taskID, String exceptionMessage) {
    super(exceptionMessage);
    mTaskID = taskID;
  }

  public AsyncVolleyError(int taskID, String exceptionMessage, Throwable reason) {
    super(exceptionMessage, reason);
    mTaskID = taskID;
  }

  public AsyncVolleyError(int taskID, Throwable cause) {
    super(cause);
    mTaskID = taskID;
  }

  public int getTaskID() {
    return mTaskID;
  }
}
