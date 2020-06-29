package com.example.weatherapp

import Utilities.Utils
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Address
import android.location.Location
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import model.Weather
import data.WeatherHttpClient
import data.JSONWeatherParser
import data.LocationPreference
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import data.JSONCoordinatesParser
import data.CoordinatesHttpClient

class MainActivity : AppCompatActivity()
{

    private var coords: String = "40.7128,-74.0060"
    private lateinit var currTemp: TextView
    private lateinit var currHumidity: TextView
    private lateinit var currPressure: TextView
    private lateinit var currWind: TextView
    private lateinit var currPrecipChance: TextView
    private lateinit var currSummary: TextView
    private lateinit var lastUpdate: TextView
    private lateinit var currCity: TextView
    private lateinit var currImg: ImageView
    private lateinit var minMax: TextView
    private lateinit var hrOneTime: TextView
    private lateinit var hrOneTemp: TextView
    private lateinit var hrOnePrecipChance: TextView

    private var curraddr: String = "Brooklyn+NY"
    


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currTemp = findViewById(R.id.temperature)
        currHumidity = findViewById(R.id.humidity)
        currPressure = findViewById(R.id.pressure)
        currWind = findViewById(R.id.wind)
        currPrecipChance = findViewById(R.id.precipChance)
        currSummary = findViewById(R.id.summary)
        lastUpdate = findViewById(R.id.update)
        currCity = findViewById(R.id.cityText)
        currImg = findViewById(R.id.thumbnailImage)
        minMax = findViewById(R.id.minmax)
        hrOneTime = findViewById(R.id.time1)
        renderWeatherData()
    }


    fun renderWeatherData()
    {
        var weatherTask = WeatherTask()
        weatherTask.execute()
    }

    inner class WeatherTask:AsyncTask<String, Void, Weather>()
    {
        override fun doInBackground(vararg params: String?): Weather
        {
            coords = JSONCoordinatesParser.getCoords(CoordinatesHttpClient.getCoordinates(curraddr))
            Log.d("test2",coords)
            var weatherData = WeatherHttpClient.getWeatherData(coords)
            return JSONWeatherParser.getWeather(weatherData)
        }

        override fun onPostExecute(weather: Weather)
        {
            super.onPostExecute(weather)

            currTemp.text = weather.currentCondition.temperature.toInt().toString()+ "°F"
            currHumidity.text= (weather.currentCondition.humidity.toString() + "%")
            currPressure.text =(weather.currentCondition.pressure.toString() + " mbar")
            currWind.text = (weather.wind.speed.toString() + " mph")
            currPrecipChance.text = (weather.snow.precipitation.toString() + "%")
            currSummary.text = weather.currentCondition.description
            currCity.text = weather.place.city

            minMax.text = weather.currentCondition.maxTemp.toString() + "°F" + "/" + weather.currentCondition.minTemp.toString() + "°F"

            val sdf = java.text.SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z")
            val date = java.util.Date(weather.currentCondition.lastUpdate * 1000)
            lastUpdate.text = sdf.format(date)

            val hrFormat = java.text.SimpleDateFormat("HH:mm")
            var date2 = java.util.Date(weather.hourlyOne.time * 1000)
            hrOneTime.text = hrFormat.format(date2)

            var date3 = java.util.Date(weather.hourlyTwo.time * 1000)
            val temperSpace = "          "
            val timeSpace = "        "

            var date4 = java.util.Date(weather.hourlyThree.time * 1000)
            var date5 = java.util.Date(weather.hourlyFour.time * 1000)
            var date6 = java.util.Date(weather.hourlyFive.time * 1000)
            var date7 = java.util.Date(weather.hourlySix.time * 1000)
            var date8 = java.util.Date(weather.hourlySeven.time * 1000)
            var date9 = java.util.Date(weather.hourlyEight.time * 1000)

            hrOneTime.append(timeSpace + hrFormat.format(date3) + timeSpace + hrFormat.format(date4) + timeSpace + hrFormat.format(date5) + timeSpace + hrFormat.format(date6) + timeSpace + hrFormat.format(date7) + timeSpace + hrFormat.format(date8) + timeSpace + hrFormat.format(date9))
            hrOneTime.append("\n" + weather.hourlyOne.temperature.toInt().toString() + "°F" + temperSpace + weather.hourlyTwo.temperature.toInt().toString()+"°F" + temperSpace + weather.hourlyThree.temperature.toInt().toString()+"°F" + temperSpace + weather.hourlyFour.temperature.toInt().toString()+ "°F" + temperSpace + weather.hourlyFive.temperature.toInt().toString() + "°F" + temperSpace + weather.hourlySix.temperature.toInt().toString() + "°F" + temperSpace + weather.hourlySeven.temperature.toInt().toString() + "°F" + temperSpace + weather.hourlyEight.temperature.toInt().toString() + "°F")



            if(currSummary.text.toString().toLowerCase().contains("thunder") || currSummary.text.toString().toLowerCase().contains("lightning"))
            {
                currImg.setImageResource(R.drawable.thunder)
            }
            else
            {
                if(currSummary.text.toString().toLowerCase().contains("foggy") || currSummary.text.toString().toLowerCase().contains("fog"))
                {
                    currImg.setImageResource(R.drawable.foggy)
                }
                else
                {
                    if(currSummary.text.toString().toLowerCase().contains("rain") || currSummary.text.toString().toLowerCase().contains("rainy") || currSummary.text.toString().toLowerCase().contains("drizzle") || currSummary.text.toString().toLowerCase().contains("raining"))
                    {
                        currImg.setImageResource(R.drawable.rain)
                    }
                    else
                    {
                        if(currSummary.text.toString().toLowerCase().contains("windy") || currSummary.text.toString().toLowerCase().contains("breeze") || currSummary.text.toString().toLowerCase().contains("wind"))
                        {
                            currImg.setImageResource(R.drawable.windy)
                        }
                        else
                        {
                            if(currSummary.text.toString().toLowerCase().contains("cloudy"))
                            {
                                currImg.setImageResource(R.drawable.cloudy)
                            }
                            else
                            {
                                currImg.setImageResource(R.drawable.sun)
                            }

                        }
                    }
                }
            }
        }
    }

    private fun showInputDialog()
    {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Change Location")
        val locText = EditText(this)
        locText.setInputType(InputType.TYPE_CLASS_TEXT)
        locText.setHint("Brooklyn+NY OR Mt+Laurel+NJ")
        builder.setView(locText)

        builder.setPositiveButton("Submit", object:DialogInterface.OnClickListener
        {
            override fun onClick(dialog:DialogInterface, which:Int)
            {
                var cityPreference = LocationPreference(this@MainActivity)
                cityPreference.setLoc(locText.getText().toString())
                curraddr = cityPreference.getLoc()

                Log.d("Hello",curraddr)
                renderWeatherData()
            }
        })
        builder.show()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        var id = item.getItemId()
        if(id == R.id.change_cityId){
            showInputDialog()
        }


        return super.onOptionsItemSelected(item)
    }
}

