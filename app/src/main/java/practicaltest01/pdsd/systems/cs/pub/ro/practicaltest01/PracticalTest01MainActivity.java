package practicaltest01.pdsd.systems.cs.pub.ro.practicaltest01;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;


public class PracticalTest01MainActivity extends Activity {

    protected EditText leftEditText, rightEditText;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener{


        @Override
        public void onClick(View v) {

            int nr_left = Integer.parseInt(leftEditText.getText().toString());
            int nr_right = Integer.parseInt(rightEditText.getText().toString());

            switch(v.getId()) {
                case R.id.left_button:
                    nr_left++;
                    leftEditText.setText(String.valueOf(nr_left));
                    break;
                case R.id.right_button:
                    nr_right++;
                    rightEditText.setText(String.valueOf(nr_right));
                    break;
                case R.id.button:
                    //se creeaza o intentie param fiind luat din AndroidManifest
                    Intent intent = new Intent("ro.pub.cs.systems.pdsd.intent.action.PracticalTest01SecondaryActivity");
                    //asociez date in Extra, cheia tre sa fie aceeasi cu am scris in a 2-a activitate la getStringExtra
                    Log.d("tag", String.valueOf(Integer.parseInt(leftEditText.getText().toString()) +
                            Integer.parseInt(rightEditText.getText().toString())));
                    intent.putExtra("nr_of_clicks", String.valueOf(Integer.parseInt(leftEditText.getText().toString()) +
                            Integer.parseInt(rightEditText.getText().toString())));
                    //se lansează în execuție activitatea prin intermediul metodei startActivityForResult() care
                    // primește ca parametru intenția și un cod de cerere, având rolul de a identifica ulterior
                    // instanța activității din care se revine.
                    startActivityForResult(intent, 2014);
                    break;
            }

            }


        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);
        leftEditText = (EditText)findViewById(R.id.editText);
        rightEditText = (EditText)findViewById(R.id.editText2);

        Button left_button = (Button)findViewById(R.id.left_button);
        left_button.setOnClickListener(buttonClickListener);

        Button right_button = (Button)findViewById(R.id.right_button);
        right_button.setOnClickListener(buttonClickListener);

        Button navigate_button = (Button)findViewById(R.id.button);
        navigate_button.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            String leftCount = savedInstanceState.getString("leftCount");
            if (leftCount != null) {
                leftEditText.setText(leftCount);
            } else {
                leftEditText.setText(String.valueOf(0));
            }
            String rightCount = savedInstanceState.getString("rightCount");
            if (rightCount != null) {
                rightEditText.setText(rightCount);
            } else {
                rightEditText.setText(String.valueOf(0));
            }
        } else {
            leftEditText.setText(String.valueOf(0));
            rightEditText.setText(String.valueOf(0));
        }


    }

    //apelată în mod automat la revenirea din activitatea invocată, aceasta primind ca parametrii:
   // 1. codul de cerere trimis în momentul în care activitatea a fost lansată în execuție;
   // 2. rezultatul furnizat de activitatea invocată;
   // 3. intenția generată de activitatea invocată.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
      //  EditText leftEditText = (EditText)findViewById(R.id.editText);
      //  EditText rightEditText = (EditText)findViewById(R.id.editText2);
        savedInstanceState.putString("leftCount", leftEditText.getText().toString());
        savedInstanceState.putString("rightCount", rightEditText.getText().toString());
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_main, menu);
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
}
