package play.dlts.projectmoonhanger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import play.dlts.projectmoonhanger.models.DBAdapter;
import play.dlts.projectmoonhanger.models.Midea;


public class MainActivity extends ActionBarActivity {
    private ListView mainlist;
    private ArrayAdapter<String> listA;
    Midea midea = new Midea();
    DBAdapter db;

    ArrayList<Integer> idea_index_holder = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpList();
    }

    public void setUpList(){
        mainlist = (ListView) findViewById(R.id.idea_list);
        listA = new ArrayAdapter<String>(this, R.layout.ideas_list, new ArrayList<String>());



        HashMap<Integer,String> map = midea.getIdeas(this);
        if (map.size() > 0){
            for (HashMap.Entry<Integer, String> entry : map.entrySet()) {
                listA.add(entry.getValue());
                idea_index_holder.add(entry.getKey());
            }
        }



        mainlist.setAdapter(listA);
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "position-" + idea_index_holder.get(position), Toast.LENGTH_LONG).show();
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

    public void newIdea(View view) {
        Intent i;
        i = new Intent(MainActivity.this, EditIdea.class);
        startActivity(i);
    }
}
