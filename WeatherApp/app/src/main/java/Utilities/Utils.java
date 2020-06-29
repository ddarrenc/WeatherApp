package Utilities;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

public class Utils
{
    public static final String BASE_URL = "https://api.darksky.net/forecast/821b7566ee092db0e19554dbced5cb91/";
            //"https://api.darksky.net/forecast/821b7566ee092db0e19554dbced5cb91/40.7128,-74.0060";
 //   public static final String BASE_URL = "https://api.darksky.net/forecast/821b7566ee092db0e19554dbced5cb91/";
    //public static final String ICON_URL = "https://api.darksky.net/forecast/821b7566ee092db0e19554dbced5cb91/";

    public static JSONObject getObject(String tag, JSONObject jsonObject) throws JSONException
    {
        JSONObject jObj = jsonObject.getJSONObject(tag);
        return jObj;
    }

    public static String getString(String tagName, JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getString(tagName);
    }
    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException
    {
        return (float)jsonObject.getDouble(tagName);
    }
    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException
    {
        return (float)jsonObject.getDouble(tagName);
    }
    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getInt(tagName);
    }
    public static JSONArray getArray(String tagName, JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getJSONArray(tagName);
    }


}
