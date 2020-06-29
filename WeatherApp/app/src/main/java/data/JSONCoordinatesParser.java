package data;

import Utilities.Utils;
import android.util.Log;
import model.Place;
import model.Weather;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONCoordinatesParser
{

    public static String getCoords(String data)
    {
        String str = "";
        try
        {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArr = Utils.getArray("results", jsonObject);
         //   Log.d("Yuh",jsonArr.toString());
            JSONObject obj  = jsonArr.getJSONObject(0);
            Log.d("mom", obj.toString());
            JSONArray jsonarr = obj.getJSONArray("locations");
            Log.d("shit",jsonarr.toString());
            JSONObject locdata = jsonarr.getJSONObject(0);
            Log.d("piss",locdata.toString());
            JSONObject latLng = Utils.getObject("latLng", locdata);
            Log.d("Brenda", latLng.toString());

            str = Utils.getString("lat",latLng) + "," + Utils.getString("lng", latLng);
            Log.d("pinky",str);
          //  Log.d("brenda",latLng.toString());


            return str;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            //return null;
        }
        return str;

    }

}
