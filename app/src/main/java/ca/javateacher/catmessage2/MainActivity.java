/* Alex Tetervak, Sheridan College, Ontario */
package ca.javateacher.catmessage2;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import static ca.javateacher.catmessage2.Constants.*;

public class MainActivity extends AppCompatActivity {

  private TextView mMessageView;
  private TextView mIsUrgentView;

  private boolean mIsMessageReceived;
  private boolean mUrgent;
  private String mMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // lookup the views
    mMessageView = findViewById(R.id.message_text);
    mIsUrgentView = findViewById(R.id.urgent_flag_output);

    Button getButton = findViewById(R.id.get_button);
    getButton.setOnClickListener(v -> showInput());

    if(savedInstanceState != null) {
      Log.v("MainActivity","The saved instance is found");
      mIsMessageReceived = savedInstanceState.getBoolean(IS_MESSAGE_RECEIVED_KEY);
      Log.v("MainActivity","mIsMessageReceived=" + mIsMessageReceived);
      if(mIsMessageReceived) {
        mUrgent = savedInstanceState.getBoolean(IS_URGENT_KEY);
        mIsUrgentView.setText(mUrgent ? R.string.urgent: R.string.not_urgent);
        mMessage = savedInstanceState.getString(MESSAGE_TEXT_KEY);
        mMessageView.setText(mMessage);
      }
    }else{
      Log.v("MainActivity","No saved instance is found");
      mIsMessageReceived = false;
    }
  }

  private void showInput(){
    Intent intent =
            new Intent(MainActivity.this, InputActivity.class);
    startActivityForResult(intent, GET_MESSAGE_REQUEST);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.v("MainActivity","Saving the instance state");
    outState.putBoolean(IS_MESSAGE_RECEIVED_KEY, mIsMessageReceived);
    Log.v("MainActivity","mIsMessageReceived=" + mIsMessageReceived);
    if(mIsMessageReceived){
      outState.putBoolean(IS_URGENT_KEY, mUrgent);
      Log.v("MainActivity","mUrgent=" + mUrgent);
      outState.putString(MESSAGE_TEXT_KEY, mMessage);
      Log.v("MainActivity","mMessage=" + mMessage);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    if (requestCode == GET_MESSAGE_REQUEST && resultCode == Activity.RESULT_OK) {
      if (intent != null) {
        mIsMessageReceived = true;
        mUrgent = intent.getBooleanExtra(IS_URGENT_KEY, true);
        mIsUrgentView.setText(mUrgent ? R.string.urgent: R.string.not_urgent);
        mMessage = intent.getStringExtra(MESSAGE_TEXT_KEY);
        mMessageView.setText(mMessage);
      }
    }
  }

}
