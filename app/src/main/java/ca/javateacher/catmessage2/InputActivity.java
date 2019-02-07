/* Alex Tetervak, Sheridan College, Ontario */
package ca.javateacher.catmessage2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class InputActivity extends AppCompatActivity {

  public static final String MESSAGE = "message";
  private RadioGroup mMessageGroup;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_input);

    mMessageGroup = findViewById(R.id.message_group);

    Button sendButton = findViewById(R.id.send_button);
    sendButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        send();
      }
    });

    Button cancelButton = findViewById(R.id.cancel_button);
    cancelButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
      }
    });
  }

  private void send(){
    // get the selected message
    String message;
    switch (mMessageGroup.getCheckedRadioButtonId()) {
      case R.id.purr_button:
        message = getString(R.string.cat_purr);
        break;
      case R.id.mew_button:
        message = getString(R.string.cat_mew);
        break;
      case R.id.hiss_button:
        message = getString(R.string.cat_hiss);
        break;
      default:
        message = getString(R.string.undefined);
    }
    // return back with the message
    Intent intent = new Intent();
    intent.putExtra(MESSAGE, message);
    setResult(Activity.RESULT_OK, intent);
    finish();
  }
}
