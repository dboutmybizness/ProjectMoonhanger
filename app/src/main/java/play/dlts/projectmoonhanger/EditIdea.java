package play.dlts.projectmoonhanger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import play.dlts.projectmoonhanger.models.Midea;


public class EditIdea extends ActionBarActivity {

    Button save_button;
    EditText title,description;
    Midea midea = new Midea();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_idea);

        title = (EditText) findViewById(R.id.etitle);
        description = (EditText) findViewById(R.id.edescription);

        clickSave();
    }

    public void clickSave(){
        save_button = (Button) findViewById(R.id.save_idea);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trySave();
            }
        });
    }

    public void trySave(){
        int ck_vals = 0;

        if (title.getText().length() < 1) ck_vals++;

        if ( ck_vals > 0) {
            Toast.makeText(this, "Title is Required", Toast.LENGTH_SHORT).show();
            return;
        }

        // save new idea
        midea.FIELD_VALUES.put("title", title.getText().toString());
        midea.FIELD_VALUES.put("description", description.getText().toString());
        midea.FIELD_VALUES.remove("_id");
        if ( midea.newIdea(this) ){
            // intent to idea
            int lastrowID = midea.LastCreatedID(this);
            Intent intent = new Intent(EditIdea.this, IdeaLanding.class);
            intent.putExtra("idea_id", lastrowID );
            startActivity(intent);
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_idea, menu);
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
