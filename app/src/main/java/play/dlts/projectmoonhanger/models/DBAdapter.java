package play.dlts.projectmoonhanger.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.SQLException;

import java.util.HashMap;

/**
 * Created by imac-darnell on 2/26/15.
 */



public class DBAdapter {

    private String TABLE;
    private String[] getFields;
    private String ORDER_BY = "";


    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "PJ_MoonHanger";
    static final int DATABASE_VERSION = 6;

    public static String[] FIELD_TYPES_ARRAY = {
            "integer primary key autoincrement", "integer", "text", "float"
    };

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;



    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }



    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            Log.w(TAG, "Creating db");

            play.dlts.projectmoonhanger.models.ModelBase[] tables = new play.dlts.projectmoonhanger.models.ModelBase[]{
                    new Midea(),
            };

            for (int i=0; i < tables.length; i++){
                play.dlts.projectmoonhanger.models.ModelBase obj = tables[i];
                db.execSQL(obj.getCreateStatement());
                HashMap<String,String> map = obj.insertInitial();
                if ( map != null) {
                    ContentValues args = new ContentValues();
                    for (HashMap.Entry <String, String> entry : map.entrySet()) {
                        args.put(entry.getKey(), entry.getValue());
                    }
                    db.insert(obj.TABLE, null, args);
                }
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrading database from version "+ oldVersion + " to " + newVersion + ", which will destroy old data");



        }
    }

    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        DBHelper.close();
    }

    public void setTable(String table) {
        this.TABLE = table;
    }
    public void setGetFields(String[] s){
        this.getFields = s;
    }

    protected void create(HashMap<String, String> map) throws SQLException{
        open();
        ContentValues args = new ContentValues();
        for (HashMap.Entry <String, String> entry : map.entrySet()) {
            args.put(entry.getKey(), entry.getValue());
        }
        db.insert(this.TABLE, null, args);
        close();
    }

    protected Boolean update(HashMap<String,String> map, String where) throws SQLException{

        ContentValues args = new ContentValues();
        for (HashMap.Entry <String, String> entry : map.entrySet()) {
            args.put(entry.getKey(), entry.getValue());
        }
        return db.update(this.TABLE, args, where, null) > 0;

    }

    protected Boolean update(String tablename, HashMap<String,String> map, String where) throws SQLException{

        ContentValues args = new ContentValues();
        for (HashMap.Entry <String, String> entry : map.entrySet()) {
            args.put(entry.getKey(), entry.getValue());
        }
        return db.update(tablename, args, where, null) > 0;

    }

    protected void create(String tablename, HashMap<String,String> map){
        ContentValues args = new ContentValues();
        for (HashMap.Entry <String, String> entry : map.entrySet()) {
            args.put(entry.getKey(), entry.getValue());
        }
        db.insert(tablename, null, args);
    }

    protected Cursor getRow(long rowId) throws SQLException{
        Cursor mCursor = db.query(true, this.TABLE, this.getFields, "_id="+rowId, null, null, null, null, null);
        return mCursor;
    }
    protected Cursor getRow(String row, long rowId) throws SQLException{
        Cursor mCursor = db.query(true, this.TABLE, this.getFields, row+"="+rowId, null, null, null, null, null);
        return mCursor;
    }
    protected Cursor getRow(String tablename,String[] getFields, String where) throws SQLException{
        Cursor mCursor = db.query(true, tablename, getFields, where, null, null, null, null, null);
        return mCursor;
    }


    protected Cursor getAllRowsWhere(String whereStatement){
        Cursor mCursor = db.query(true, this.TABLE, this.getFields, whereStatement, null,null,null,null,null);
        return mCursor;
    }

    protected Cursor getAllRows(String tablename, String[] getFields, String where){
        Cursor mCursor = db.query(true, tablename, getFields, where, null,null,null,null,null);
        return mCursor;
    }

    protected Cursor getRowIds(){
        String[] cols = {"_id"};
        Cursor mCursor = db.query(true, this.TABLE, cols, null, null, null, null, this.ORDER_BY, null);
        return mCursor;
    }

    protected Cursor getLastRow(String tablename, String[] getFields){
        Cursor mCursor = db.query(true, tablename, getFields, null,null,null,null,"_id desc", "1");
        return mCursor;
    }


}
