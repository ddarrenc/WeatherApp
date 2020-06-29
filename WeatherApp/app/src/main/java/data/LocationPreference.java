package data;

import android.app.Activity;
import android.content.SharedPreferences;

public class LocationPreference
{
    SharedPreferences prefs;

    public LocationPreference(Activity activity)
    {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getLoc()
    {
        return prefs.getString("city","Brooklyn+NY");
    }

    public void setLoc(String city)
    {
        prefs.edit().putString("city", city).commit();
    }
}
