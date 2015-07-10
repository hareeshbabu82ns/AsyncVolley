# AsyncVolley

Project is to extend Volley usage to perform AsyncTasks with any Object Type.

## Setup & Running

1. Clone the Android Studio project
```bash
  git clone https://github.com/hareeshbabu82ns/AsyncVolley.git
```
1. Or Copy classes in com.har.volley package into Existing Project
1. Clone the Volley Project as Library project into Android Studio
```bash
  git clone https://android.googlesource.com/platform/frameworks/volley
```
1. Instantiate Volley AsyncQueue with ApplicationContext
```java
  //only to initialize
  com.har.volley.Volley.getAsyncQueue(applicationContext);
  //later just call to get singleton Queue
  com.har.volley.Volley.getAsyncQueue();
```
1. Need to perform a AsyncTask
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
1. Implement com.har.volley.AsyncTaskRequest.AsyncListener<T> to receive callbacks
```java
    @Override
    public String performRequest(AsyncTaskRequest<String> request) throws Exception {
      //long running task
    }
    @Override
    public void onResponse(String response) {
      Toast.makeText(MainActivity.this, error.getLocalizedMessage(),
            Toast.LENGTH_SHORT).show();
      progress.setVisibility(View.GONE);
    }
```
1. Implement Response.ErrorListener to receive VolleyError
```java
  @Override
  public void onErrorResponse(VolleyError error) {
    Toast.makeText(MainActivity.this, error.getLocalizedMessage(),
          Toast.LENGTH_SHORT).show();
    progress.setVisibility(View.GONE);
  }
```


## Author

Hareesh Polla
 - [Twitter @hareeshbabu82](https://twitter.com/hareeshbabu82)
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