package com.har.asyncvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.har.asyncvolleylib.AsyncTaskRequest;
import com.har.asyncvolleylib.Volley;

public class MainActivity extends AppCompatActivity implements AsyncTaskRequest
    .AsyncListener<String>, Response.ErrorListener {

  private TextView txtMsg;
  private Button butFetch;
  private ProgressBar progress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    int id = 1;
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    txtMsg = (TextView) findViewById(R.id.txtMessage);
    butFetch = (Button) findViewById(R.id.butFetch);
    progress = (ProgressBar) findViewById(R.id.progressBar);

    butFetch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        progress.setVisibility(View.VISIBLE);
        Volley.getAsyncQueue().add(
            new AsyncTaskRequest<String>((int) System.currentTimeMillis(),
                MainActivity.this, MainActivity.this)
                .setTag("SillyTask"));
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  final StringBuffer buf = new StringBuffer();

  @Override
  public void onResponse(String response) {
    buf.append(response).append("\n");
    txtMsg.setText(buf.toString());
    progress.setVisibility(View.GONE);
  }

  @Override
  public String performRequest(AsyncTaskRequest<String> request) throws Exception {
    long end = System.currentTimeMillis() + 5000;
    while (end > System.currentTimeMillis()) {
      //
    }
    return "Performed Async Task at " + System.currentTimeMillis();
  }

  @Override
  public void onErrorResponse(VolleyError error) {
    Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    progress.setVisibility(View.GONE);
  }
}
