/* Alex Tetervak, Sheridan College, Ontario */
package ca.javateacher.catmessage2;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  // the request code
  private static final int GET_MESSAGE = 0;

  // to save the instance state
  private static final String MESSAGE = "message";

  private String mMessageText;
  private TextView mMessageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mMessageView = findViewById(R.id.message_text);

    Button getButton = findViewById(R.id.get_button);
    getButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent =
            new Intent(MainActivity.this, InputActivity.class);
        startActivityForResult(intent, GET_MESSAGE);
      }
    });

    if(savedInstanceState != null){
      mMessageText = savedInstanceState.getString(MESSAGE);
      mMessageView.setText(mMessageText);
    }else{
      mMessageText = getString(R.string.undefined);
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(MESSAGE,mMessageText);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == GET_MESSAGE && resultCode == Activity.RESULT_OK) {
      if(data != null) {
        mMessageText = data.getStringExtra(InputActivity.MESSAGE);
        mMessageView.setText(mMessageText);
      }
    }
  }

}
