package play.dlts.projectmoonhanger.models;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

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

    public HashMap<Integer,String> getIdeas(Context ctx) {
        HashMap<Integer,String> map = new HashMap<Integer,String>();
        Cursor c;
        DBAdapter db = new DBAdapter(ctx);
        try{
            db.open();
            c = db.getAllRows(this.TABLE, this.FIELDS_LIST_ARRAY, null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    map.put(c.getInt(0), c.getString(1));

                } while (c.moveToNext());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    public HashMap<String,String> getRow(Context ctx, Long id){
        return getRowByID(ctx,id);
    }


    public Boolean newIdea(Context ctx){
        return super.createRow(ctx);
    }

    public int LastCreatedID(Context ctx){
        Cursor c;
        int lastID = 0;
        DBAdapter db = new DBAdapter(ctx);
        try{
            db.open();
            c = db.getLastRow(this.TABLE, this.FIELDS_LIST_ARRAY);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    lastID = c.getInt(0);

                } while (c.moveToNext());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lastID;
    }
}
