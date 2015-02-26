package play.dlts.projectmoonhanger.models;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class Midea extends ModelBase {

	static final String TABLE = "profile";
	static final String[] FIELDS_ARRAY = {
		"_id:0",
		"title:2",
        "description:2",
        "created_time:1"
	};
	
	public Midea() {
		super(FIELDS_ARRAY, TABLE);
	}

	@Override
	public HashMap<String,String> insertInitial(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put(FIELD_NAMES.get(1), "Your First Idea");
		map.put(FIELD_NAMES.get(2), "This is just a test idea");
        map.put(FIELD_NAMES.get(3), "4234323");
		return map;
	}

    public ArrayList<String> getAllIdeas(Context ctx) {
        DBAdapter db = new DBAdapter(ctx);
        Cursor c = null;
        ArrayList<String> alist = new ArrayList<String>();

        try {
            db.open();
            c = db.getAllRows(Midea.TABLE, this.FIELDS_LIST_ARRAY, null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                Log.w("testing here", c.getString(1));
                do {
                    alist.add(c.getString(1));
                } while (c.moveToNext());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.close();

        return alist;
    }
}
