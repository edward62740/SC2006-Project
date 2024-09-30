package com.nutriroute.utils;

import android.os.AsyncTask;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.xml.sax.InputSource;

public class GNSSLocHelper {

    public void fromAddressGetCoordinates(String address, AddressCallback callback) {
        new GetCoordinatesTask(address, callback).execute();
    }

    private static class GetCoordinatesTask extends AsyncTask<Void, Void, String> {
        private String address;
        private AddressCallback callback;

        GetCoordinatesTask(String address, AddressCallback callback) {
            this.address = address;
            this.callback = callback;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String urlString = "https://geocode.xyz";
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setDoOutput(true);

                // Create the POST data
                String postData = "locate=" + address + "&geoit=XML"; // Correct format based on curl
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(postData);
                wr.flush();
                wr.close();

                // Read the response
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
                    // Parse the XML response
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(new InputSource(new StringReader(response)));

                    // Extract latitude and longitude from the XML
                    NodeList latNodes = doc.getElementsByTagName("lat");
                    NodeList lngNodes = doc.getElementsByTagName("lng");

                    if (latNodes.getLength() > 0 && lngNodes.getLength() > 0) {
                        String latitude = latNodes.item(0).getTextContent();
                        String longitude = lngNodes.item(0).getTextContent();
                        callback.onCoordinatesReceived(latitude, longitude);
                    } else {
                        callback.onCoordinatesReceived(null, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onCoordinatesReceived(null, null);
                }
            } else {
                callback.onCoordinatesReceived(null, null);
            }
        }
    }

    public interface AddressCallback {
        void onCoordinatesReceived(String latitude, String longitude);
    }
}
