package practicaltest01.pdsd.systems.cs.pub.ro.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondaryActivity extends Activity {

    protected TextView textView;
    protected Button ok, cancel;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {


        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.ok_button:
                    setResult(RESULT_OK, new Intent());
                    finish();
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, new Intent());
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        ok = (Button)findViewById(R.id.ok_button);
        cancel = (Button)findViewById(R.id.cancel_button);

        ok.setOnClickListener(buttonClickListener);
        cancel.setOnClickListener(buttonClickListener);

        textView =(TextView)findViewById(R.id.number_of_clicks_text_view);

        Intent intent  =  getIntent();

        if(intent!=null) {

            String nr_clicks = intent.getStringExtra("nr_of_clicks");
            System.out.println("NR CLICKS  " + nr_clicks);
            if (nr_clicks != null) {
                textView.setText(nr_clicks);
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_secondary, menu);
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
