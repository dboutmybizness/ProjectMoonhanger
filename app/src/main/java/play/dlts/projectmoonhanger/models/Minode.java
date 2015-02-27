package play.dlts.projectmoonhanger.models;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.HashMap;


public class Minode extends ModelBase {

	static final String TABLE = "nodes";
	static final String[] FIELDS_ARRAY = {
		"_id:0",
		"title:2",
        "description:2",
        "created_time:1"
	};

	public Minode() {
		super(FIELDS_ARRAY, TABLE);
	}

	@Override
	public HashMap<String,String> insertInitial(){
		return null;
	}


}
