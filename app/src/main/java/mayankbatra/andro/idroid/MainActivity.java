package mayankbatra.andro.idroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;



public class MainActivity extends Activity {
    Switch switch1;
    String temp;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1=(Switch)findViewById(R.id.switch1);
        switch1.setChecked(true);
        sp= PreferenceManager.getDefaultSharedPreferences(this);

        temp = sp.getString("Cache_Status", "ON");
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked)
                {
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("Cache_Status", "ON");
                    edit.commit();
                    Toast.makeText(getApplicationContext(),"Application is ON ",Toast.LENGTH_LONG).show();
                } else
                {

                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("Cache_Status", "OFF");
                    edit.commit();
                    Toast.makeText(getApplicationContext(),"Application is Off ",Toast.LENGTH_LONG).show();
                }

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
}
