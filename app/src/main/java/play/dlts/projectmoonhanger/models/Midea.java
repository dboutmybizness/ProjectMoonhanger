package play.dlts.projectmoonhanger.models;

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

	

}
