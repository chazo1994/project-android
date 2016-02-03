package Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by thinh on 11/8/2015.
 */
public class DatabaseAdapter extends SQLiteOpenHelper {
    /*
    * DB_PATH: path hold folder,where app read database file
    * "com.example.thinh.ghis" is your package, you can see it in file manifest*/
    private String DB_PATH = "data/data/com.example.thinh.ghis/";

    /*
    * DB_NAME: name of datebase*/
    private static String DB_NAME = "ghis.sqlite";
    /*
    * ghisDatabase is my database*/
    private SQLiteDatabase ghisDatabase;
    private static final int DB_VERSION = 1;
    private Context context;

    public DatabaseAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseAdapter(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        boolean checkDbexist = checkDatabase();

        this.context = context;
        if(checkDbexist){
            // do something
        }
        else {
            creatDatabase();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void creatDatabase(){
        try {
            copyDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
/* copy database from assets folder to data path folder
*/
    private void copyDatabase() throws IOException {
        AssetManager dirPath = context.getAssets();
        InputStream inputStreamFileDB = context.getAssets().open(DB_NAME);
        String outFileNameDB = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(DB_PATH+DB_NAME);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStreamFileDB.read(buffer))>0){
            outputStream.write(buffer,0,length);
        }

        outputStream.flush();
        outputStream.close();
        inputStreamFileDB.close();
    }

    public void openDatabase(){
        String myPath = DB_PATH+DB_NAME;
        ghisDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDatabase(){
        ghisDatabase.close();
        super.close();
    }
    /*
    * Function checkdatabase check database in filePath,
    * if database locate in folder at filepath, we use this database
    * else, copy data base from folder assets to folder at filePath by method copyDatabase*/
    private boolean checkDatabase(){
        boolean checkdb = false;

        try {
            String filePath = DB_PATH + DB_NAME;
            File dbFile = new File(filePath);
            checkdb = dbFile.exists();

        } catch (SQLiteException e){
            return checkdb;
        }
        return checkdb;
    }

    public SQLiteDatabase getMyDatabase(){
        return ghisDatabase;
    }
}
