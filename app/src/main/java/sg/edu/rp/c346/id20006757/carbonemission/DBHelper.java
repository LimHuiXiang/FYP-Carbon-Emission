package sg.edu.rp.c346.id20006757.carbonemission;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "emission.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_EMISSION = "emission";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "_date";
    private static final String COLUMN_VC = "vehicle_code";
    private static final String COLUMN_FUEL ="fuel";
    private static final String COLUMN_PASSENGER = "passenger";
    private static final String COLUMN_DISTANCE = "distance";
    private static final String COLUMN_CO2E = "co2e";

    public DBHelper(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating the database
        String createEmissionTableSql = "CREATE TABLE " + TABLE_EMISSION + "("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATE  + " TEXT,"+ COLUMN_VC + " TEXT," + COLUMN_FUEL +" TEXT," + COLUMN_PASSENGER + " TEXT ,"
                + COLUMN_DISTANCE + " TEXT," + COLUMN_CO2E + " TEXT)";
        db.execSQL(createEmissionTableSql);
        Log.i("info" ,"SQL statement: " + createEmissionTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Update / Delete exisiting table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMISSION);
        db.execSQL("ALTER TABLE " + TABLE_EMISSION + " ADD COLUMN  module_name TEXT ");
        //onCreate(db); // Delete as table already created
        onCreate(db);
    }
    //Method to insert the user inputs into the database
    public long InsertEmission(String date, String vehicle_code, String fuel, String passenger, String distance, String co2e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_VC, vehicle_code);
        values.put(COLUMN_FUEL, fuel);
        values.put(COLUMN_PASSENGER, passenger);
        values.put(COLUMN_DISTANCE, distance);
        values.put(COLUMN_CO2E, co2e);
        long result = db.insert(TABLE_EMISSION, null, values);
        db.close();
        // Log message for error troubleshooting in logcat
        Log.d("SQL Insert", "ID:" + result); //id returned, should not  be -1
        return result;
    }

    //Method to get all Emission records in database.
    public ArrayList<Emission> getAllEmission() {
        ArrayList<Emission> emissions = new ArrayList<Emission>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_DATE,COLUMN_VC,COLUMN_FUEL, COLUMN_PASSENGER, COLUMN_DISTANCE, COLUMN_CO2E};
        Cursor cursor = db.query(TABLE_EMISSION, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String vehicle_code = cursor.getString(2);
                String fuel = cursor.getString(3);
                int passenger = cursor.getInt(4);
                Double distance = cursor.getDouble(5);
                Double co2e = cursor.getDouble(6);
                Emission emission = new Emission(id, date, vehicle_code,fuel, distance, co2e);
                emissions.add(emission);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return emissions;
    }

    //Method to delete Emission record from the database based on ID
    public int deleteEmission(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_EMISSION, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> getVCLabels(){
        ArrayList<String> vcLabels = new ArrayList<>();
        vcLabels.add("NONE");
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_VC};
        Cursor cursor = db.query(true, TABLE_EMISSION, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String vc = cursor.getString(0);
                vcLabels.add(vc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return vcLabels;
    }

    //Method to search for record based of Vehicle code
    public ArrayList< Emission> getAllEmissionByVehicleCode(String VehicleCode) {
        ArrayList<Emission> vehiclelist = new ArrayList<Emission>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_DATE,COLUMN_VC, COLUMN_FUEL, COLUMN_DISTANCE, COLUMN_CO2E};
        String condition = COLUMN_VC + "= ?";
        String[] args = {VehicleCode};

        Cursor cursor;
        cursor = db.query(TABLE_EMISSION, columns, condition, args, null, null, null, null);

       // Loops through all row and adds to arrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String vehicle_code = cursor.getString(2);
                String fuel = cursor.getString(3);
                Double distance = cursor.getDouble(4);
                Double co2e = cursor.getDouble(5);
                Emission newEmission = new Emission(id, date, vehicle_code, fuel, distance, co2e);
                vehiclelist.add(newEmission);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return vehiclelist;
    }
    public ArrayList<String>GetID(){
        ArrayList<String> ID = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID};
        Cursor cursor = db.query(true, TABLE_EMISSION, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String id2 = String.valueOf(id);
                ID.add(id2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ID;
    }

    //Method to search for record based of Vehicle code
    public ArrayList< Emission> editBasedOnID(String ID) {
        ArrayList<Emission> IDlist = new ArrayList<Emission>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_DATE,COLUMN_VC, COLUMN_FUEL, COLUMN_DISTANCE, COLUMN_CO2E};
        String condition = COLUMN_ID + "= ?";
        String [] args = {ID};

        Cursor cursor;
        cursor = db.query(TABLE_EMISSION, columns, condition, args, null, null, null, null);

        // Loops through all row and adds to arrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String vehicle_code = cursor.getString(2);
                String fuel = cursor.getString(3);
                Double distance = cursor.getDouble(4);
                Double co2e = cursor.getDouble(5);
                Emission newEmission = new Emission(id, date, vehicle_code, fuel, distance, co2e);
                IDlist.add(newEmission);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return IDlist;
    }




}

