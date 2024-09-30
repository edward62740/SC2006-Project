package com.nutriroute.utils;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostalCodeHelper {

    public void fromPostalCodeGetAddress(int postalCode, PostalCodeCallback callback) {
        new GetAddressTask(postalCode, callback).execute();
    }

    private static class GetAddressTask extends AsyncTask<Void, Void, String> {
        private int postalCode;
        private PostalCodeCallback callback;

        GetAddressTask(int postalCode, PostalCodeCallback callback) {
            this.postalCode = postalCode;
            this.callback = callback;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String urlString = "https://www.onemap.gov.sg/api/common/elastic/search?searchVal=" + postalCode + "&returnGeom=Y&getAddrDetails=Y";
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray results = jsonResponse.getJSONArray("results");
                    if (results.length() > 0) {
                        JSONObject firstResult = results.getJSONObject(0);
                        String address = firstResult.getString("ADDRESS");
                        callback.onAddressReceived(address);
                    } else {
                        callback.onAddressReceived(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onAddressReceived(null);
                }
            } else {
                callback.onAddressReceived(null);
            }
        }
    }

    public interface PostalCodeCallback {
        void onAddressReceived(String address);
    }
}
