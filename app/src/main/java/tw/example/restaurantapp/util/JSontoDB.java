package tw.example.restaurantapp.util;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class JSontoDB {

    private String jsonString;
    private SQLiteDatabase db;

    // 接收 JSON 後寫入 DB
    // 接收需求: JSON &　SQLite DB

    public JSontoDB(SQLiteDatabase db){
        this.db= db;
    }
    public void writeToDatabase(String jsonString){
        this.jsonString = jsonString;
        //轉換JSON 取出 JSONArray
        try {
            JSONObject rawData = new JSONObject(jsonString);
            JSONArray records = rawData.getJSONObject("XML_Head").getJSONObject("Infos").getJSONArray("Info");
            for(int i=0; i< records.length() ; i++) {
                JSONObject obj = records.getJSONObject(i);
                db.execSQL("insert into restaurant values(?,?,?,?,?,?,?,?);",
                        new Object[] {
                                obj.getString("Id"),
                                obj.getString("Name"),
                                obj.getString("Description"),
                                obj.getString("Region"),
                                obj.getString("Town"),
                                obj.getString("Add"),
                                obj.getString("Tel"),
                                obj.getString("Opentime")
                        });
                //Log.d("JSON" , obj.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
