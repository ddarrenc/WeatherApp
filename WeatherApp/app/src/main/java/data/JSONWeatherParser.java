package data;

import Utilities.Utils;
import android.util.Log;
import model.Place;
import model.Weather;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONWeatherParser
{
    public static Weather getWeather(String data)
    {
        Weather weather = new Weather();
        try {
            JSONObject jsonObject = new JSONObject(data);
            Place place = new Place();

            JSONObject current = Utils.getObject("currently", jsonObject);
            weather.currentCondition.setTemperature(Utils.getDouble("temperature", current));
            weather.currentCondition.setPressure(Utils.getFloat("pressure",current));
            weather.currentCondition.setVisibility(Utils.getFloat("visibility",current));
            weather.currentCondition.setHumidity(Utils.getFloat("humidity",current));
            weather.currentCondition.setDescription(Utils.getString("summary",current));
            weather.currentCondition.setIcon(Utils.getString("icon",current));
            weather.place.setCity(Utils.getString("timezone",jsonObject));
            weather.wind.setSpeed(Utils.getFloat("windSpeed",current));
            weather.snow.setPrecipitation((int)Utils.getDouble("precipProbability",current));
            weather.currentCondition.setLastUpdate(Utils.getInt("time",current));

            JSONObject daily = Utils.getObject("daily", jsonObject);
            JSONArray dailyArr = Utils.getArray("data", daily);
            JSONObject today = dailyArr.getJSONObject(0);
            weather.currentCondition.setMinTemp((int)Utils.getDouble("temperatureLow", today));
            weather.currentCondition.setMaxTemp((int)Utils.getDouble("temperatureHigh", today));


            JSONObject hourly = Utils.getObject("hourly", jsonObject);
            JSONArray hourlyArr = Utils.getArray("data", hourly);

            JSONObject hrOne = hourlyArr.getJSONObject(0);
            weather.hourlyOne.setTime(Utils.getInt("time",hrOne));
            weather.hourlyOne.setDescription(Utils.getString("summary",hrOne));
            weather.hourlyOne.setIcon(Utils.getString("icon",hrOne));
            weather.hourlyOne.precip.setPrecipitation((int)Utils.getDouble("precipProbability", hrOne));
            weather.hourlyOne.setTemperature(Utils.getDouble("temperature", hrOne));

            JSONObject hrTwo = hourlyArr.getJSONObject(1);
            weather.hourlyTwo.setTime(Utils.getInt("time",hrTwo));
            weather.hourlyTwo.setDescription(Utils.getString("summary",hrTwo));
            weather.hourlyTwo.setIcon(Utils.getString("icon",hrTwo));
            weather.hourlyTwo.precip.setPrecipitation((int)Utils.getDouble("precipProbability", hrTwo));
            weather.hourlyTwo.setTemperature(Utils.getDouble("temperature", hrTwo));

            JSONObject hrThree = hourlyArr.getJSONObject(2);
            weather.hourlyThree.setTime(Utils.getInt("time",hrThree));
            weather.hourlyThree.setDescription(Utils.getString("summary",hrThree));
            weather.hourlyThree.setIcon(Utils.getString("icon",hrThree));
            weather.hourlyThree.precip.setPrecipitation((int)Utils.getDouble("precipProbability", hrThree));
            weather.hourlyThree.setTemperature(Utils.getDouble("temperature", hrThree));


            JSONObject hrFour = hourlyArr.getJSONObject(3);
            weather.hourlyFour.setTime(Utils.getInt("time",hrFour));
            weather.hourlyFour.setDescription(Utils.getString("summary",hrFour));
            weather.hourlyFour.setIcon(Utils.getString("icon",hrFour));
            weather.hourlyFour.precip.setPrecipitation((int)Utils.getDouble("precipProbability", hrFour));
            weather.hourlyFour.setTemperature(Utils.getDouble("temperature", hrFour));

            JSONObject hrFive = hourlyArr.getJSONObject(4);
            weather.hourlyFive.setTime(Utils.getInt("time",hrFive));
            weather.hourlyFive.setDescription(Utils.getString("summary",hrFive));
            weather.hourlyFive.setIcon(Utils.getString("icon",hrFive));
            weather.hourlyFive.precip.setPrecipitation((int)Utils.getDouble("precipProbability", hrFive));
            weather.hourlyFive.setTemperature(Utils.getDouble("temperature", hrFive));

            JSONObject hrSix = hourlyArr.getJSONObject(5);
            weather.hourlySix.setTime(Utils.getInt("time",hrSix));
            weather.hourlySix.setDescription(Utils.getString("summary",hrSix));
            weather.hourlySix.setIcon(Utils.getString("icon",hrSix));
            weather.hourlySix.precip.setPrecipitation((int)Utils.getDouble("precipProbability", hrSix));
            weather.hourlySix.setTemperature(Utils.getDouble("temperature", hrSix));

            JSONObject hrSeven = hourlyArr.getJSONObject(6);
            weather.hourlySeven.setTime(Utils.getInt("time",hrSeven));
            weather.hourlySeven.setDescription(Utils.getString("summary",hrSeven));
            weather.hourlySeven.setIcon(Utils.getString("icon",hrSeven));
            weather.hourlySeven.precip.setPrecipitation((int)Utils.getDouble("precipProbability", hrSeven));
            weather.hourlySeven.setTemperature(Utils.getDouble("temperature", hrSeven));

            JSONObject hrEight = hourlyArr.getJSONObject(7);
            weather.hourlyEight.setTime(Utils.getInt("time",hrEight));
            weather.hourlyEight.setDescription(Utils.getString("summary",hrEight));
            weather.hourlyEight.setIcon(Utils.getString("icon",hrEight));
            weather.hourlyEight.precip.setPrecipitation((int)Utils.getDouble("precipProbability", hrEight));
            weather.hourlyEight.setTemperature(Utils.getDouble("temperature", hrEight));

            return weather;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            //return null;
        }
        return weather;
    }
}
