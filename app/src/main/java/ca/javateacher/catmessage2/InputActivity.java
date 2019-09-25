/* Alex Tetervak, Sheridan College, Ontario */
package ca.javateacher.catmessage2;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import static ca.javateacher.catmessage2.Constants.*;

public class InputActivity extends AppCompatActivity {

  private CheckBox mUrgentCheckBox;
  private RadioGroup mMessageGroup;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_input);

    // lookup the views
    mUrgentCheckBox = findViewById(R.id.urgent_check_box);
    mMessageGroup = findViewById(R.id.message_group);

    // make the buttons work
    Button cancelButton = findViewById(R.id.cancel_button);
    cancelButton.setOnClickListener(v -> cancel());
    Button sendButton = findViewById(R.id.send_button);
    sendButton.setOnClickListener(v -> send());
  }

  private void cancel(){
    setResult(Activity.RESULT_CANCELED);
    finish();
  }

  private void send(){
    // get urgent flag value
    boolean urgent = mUrgentCheckBox.isChecked();
    // get the selected message text
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
    // return back with the message data
    Intent intent = new Intent();
    intent.putExtra(IS_URGENT_KEY, urgent);
    intent.putExtra(MESSAGE_TEXT_KEY, message);
    setResult(Activity.RESULT_OK, intent);
    finish();
  }
}
