# AsyncVolley

Project is to extend Volley usage to perform AsyncTasks with any Object Type.

## Setup & Running

1. Clone the Android Studio project
```bash
  git clone https://github.com/hareeshbabu82ns/AsyncVolley.git
```
2. Copy the **AsyncVolleyLib** library project into existing project
3. Clone the Volley Project as Library project into Android Studio
```bash
  git clone https://android.googlesource.com/platform/frameworks/volley
```
4. Instantiate Volley AsyncQueue with ApplicationContext
```java
  //only to initialize
  com.har.asyncvolleylib.Volley.getAsyncQueue(applicationContext);
  //later just call to get singleton Queue
  com.har.asyncvolleylib.Volley.getAsyncQueue();
```
5. Need to perform a AsyncTask
```java
      buttonFetch.setOnClickListener( (){
        ... (){
          progress.setVisibility(View.VISIBLE);
          Volley.getAsyncQueue().add(
              new AsyncTaskRequest<String>(taskID,
                  AsyncTaskRequest.AsyncListener<String>,
                  Response.ErrorListener)
                  .setTag("TaskTag"));
        }});
```
6. Implement com.har.asyncvolleylib.AsyncTaskRequest.AsyncListener<T> to receive callbacks
```java
    @Override
    public String performRequest(int taskID, AsyncTaskRequest<String> request) throws Exception {
      //long running task
    }
    @Override
    public void onResponse(int taskID, String response) {
      Toast.makeText(MainActivity.this, response,
            Toast.LENGTH_SHORT).show();
      progress.setVisibility(View.GONE);
    }
```
7. Implement Response.ErrorListener to receive AsyncVolleyError which extends VolleyError
```java
  @Override
  public void onErrorResponse(VolleyError error) {
    final taskID = ((AsyncVolleyError)error).getTaskID();
    Toast.makeText(MainActivity.this, error.getLocalizedMessage(),
          Toast.LENGTH_SHORT).show();
    progress.setVisibility(View.GONE);
  }
```


## Author

Hareesh Polla
 - [LinkedIn](https://in.linkedin.com/in/hareeshbabu82)

## License

AsyncVolley is licensed under the Apache 2 license (you can use it in commercial and open source
projects).

```
Copyright 2015 Hareesh Polla

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```