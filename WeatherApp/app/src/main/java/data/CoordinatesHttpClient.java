package data;
import Utilities.Utils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CoordinatesHttpClient
{
    public static String getCoordinates(String input)
    {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try{
            connection = (HttpURLConnection) (new URL("https://www.mapquestapi.com/geocoding/v1/address?key=nAYpOMZvQ2P3DYGFlaAuvRNSZWiyl0Dz&inFormat=kvp&outFormat=json&location=" + input )).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();

            // bufferedreader can only read the input stream of bits
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();

            Log.d("data",stringBuffer.toString());
            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error fetching data";
    }
}
