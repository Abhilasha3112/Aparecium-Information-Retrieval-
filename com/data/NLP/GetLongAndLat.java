package com.data.NLP;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.apache.http.util.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class GetLongAndLat {




private static String getRequest(String url) throws Exception {

        final URL obj = new URL(url);
        final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static String getCoordinates(String address) {


        Map<String, Double> res;
        StringBuffer query;
        StringBuilder s=new StringBuilder();
        s.append(address);
        if(!address.contains("India"))
            s.append(" India");
        address=s.toString();
        String[] split = address.split(" ");
        String queryResult = null;

        query = new StringBuffer();
        res = new HashMap<String, Double>();

        query.append("https://maps.googleapis.com/maps/api/geocode/json?address=");
        //https://nominatim.openstreetmap.org/search.php?q=panaji+goa+india&format=json
        if (split.length == 0) {
            return null;
        }

        for (int i = 0; i < split.length; i++) {
           query.append(split[i]);
            if (i < (split.length - 1)) {
              query.append("+");
            }
        }
      query.append("&key=Your-API-KEY");

        //log.debug("Query:" + query);

        URL url = null;
        try {
            url = new URL(query.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Response res1 = given().
                    when().
                    get(url);
            JsonPath jp = new JsonPath(res1.asString());
            String lat = jp.get("results.geometry.location.lat").toString();
            String lng = jp.get("results.geometry.location.lng").toString();

            String[] location = new String[2];
            location[0] = lat;
            location[1] = lng;
            String country=getAddress(lat,lng);
            if(country.equals("") || country==null || !country.equalsIgnoreCase("india"))
            {
                System.out.println(country);
                return location[0] + "," + location[1];
            }
            else
            return location[0] + "," + location[1];
        }
        catch(Exception e)
        {
            return "0,0";
        }
    }
    public static String getAddress(String latitude,String longitude) {

        String Country = "";
      StringBuilder  query = new StringBuilder();
      String quer="https://maps.googleapis.com/maps/api/geocode/json?latlng="+ latitude.substring(1,latitude.length()-1) + "," +
              longitude.substring(1,longitude.length()-1) + "&sensor=true&key=Your-API-KEY";
        URL url=null;
        try {
            try {
                url = new URL(quer.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                Response res1 = given().
                        when().
                        get(url);
                JsonPath jp = new JsonPath(res1.asString());
               // String lat = jp.get("results.geometry.location.lat").toString();
                //String lng = jp.get("results.geometry.location.lng").toString();

                //String[] location = new String[2];
                //location[0] = lat;
                //location[1] = lng;


                String Status = jp.getString("status");

                if (Status.equalsIgnoreCase("OK")) {
                   //
                    ArrayList<String> a=jp.getJsonObject("results");
                    JSONArray Results=new JSONArray(a);
                    //JSONObject jsonObj =jp.getJsonObject("results");
                    //JSONArray Results = jsonObj.getJSONArray("results");
                    JSONObject zero = Results.getJSONObject(0);
                    JSONArray address_components = zero.getJSONArray("address_components");

                    for (int i = 0; i < address_components.length(); i++) {
                        JSONObject zero2 = address_components.getJSONObject(i);
                        String long_name = zero2.getString("long_name");
                        JSONArray mtypes = zero2.getJSONArray("types");
                        String Type = mtypes.getString(0);

                        if (TextUtils.isEmpty(long_name) == false || !long_name.equals(null) || long_name.length() > 0 || long_name != "") {
                            if (Type.equalsIgnoreCase("country")) {
                                Country = long_name;
                            }
                        }

                        // JSONArray mtypes = zero2.getJSONArray("types");
                        // String Type = mtypes.getString(0);
                        // Log.e(Type,long_name);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {

        }
        return Country;
    }


    public static void main(String[] args) {
        String coords;
     //   System.out.println("latitude :" );
        coords = GetLongAndLat.getCoordinates("Kumbh India India");
        System.out.println("latitude :" + coords);
       // getAddress();
      /*  URL urlForGetRequest = null;
        try {
            urlForGetRequest = new URL("" +"https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCJIjV_jpnPWa-MSId1dWEDgK9rqHTTzBM");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String readLine = null;
        HttpURLConnection conection = null;
        try {
            conection = (HttpURLConnection) urlForGetRequest.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        // conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = 0;

            try {
                responseCode = conection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = null;
                try {
                    in = new BufferedReader(
                            new InputStreamReader(conection.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StringBuffer response = new StringBuffer();
                while (true) {
                    try {
                        if (!((readLine = in.readLine()) != null)) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    response.append(readLine);
                }
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // print result
        System.out.println("latitude :" + coords.get("lat"));
        System.out.println("longitude:" + coords.get("lon"));*/
    }
}


