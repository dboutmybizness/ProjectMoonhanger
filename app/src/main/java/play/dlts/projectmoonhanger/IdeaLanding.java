package play.dlts.projectmoonhanger;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import play.dlts.projectmoonhanger.models.Midea;


public class IdeaLanding extends ActionBarActivity {
    int idea_id;
    Midea midea = new Midea();
    HashMap<String,String> idea_row;
    TextView title,descrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_landing);

        Bundle bundle = getIntent().getExtras();
        idea_id = bundle.getInt("idea_id");


        if ( idea_id > 0 ){

            idea_row = midea.getRow(this, (long) idea_id);
            title = (TextView) findViewById(R.id.ilanding_title);
            title.setText(idea_row.get("title"));
            descrip = (TextView) findViewById(R.id.ilanding_desc);
            descrip.setText(idea_row.get("description"));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_idea_landing, menu);
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
