package com.jeffreyw.KFG.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.jeffreyw.KFG.KFG;

import javax.net.ssl.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import com.google.gson.*;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpResponseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// FROM https://github.com/TGWaffles/iTEM/blob/8b52fe19a706196d168a73b56078730aac9a5bd6/src/main/java/club/thom/tem/util/RequestUtil.java


public class Requests {
    private static final Logger logger = LogManager.getLogger(Requests.class);
    private final  String baseurl = "https://sb.jeffrey.hackclub.app";

    public static SSLSocketFactory getAllowAllFactory() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            return sc.getSocketFactory();
        } catch (Exception ignored) {
        }
        return null;
    }

    private RequestData printErrorDebug(String url, String jsonAsText, int status, Exception e) {
        logger.error("Exception when fetching data... (uc maybe null)", e);
        logger.error("URL was: {}", url != null ? url : "null url");
        logger.error("Json data: {}", jsonAsText);
        JsonObject errorObject = new JsonObject();
        errorObject.addProperty("success", false);
        errorObject.addProperty("status", status);
        return new RequestData(status, new HashMap<String, List<String>>(), errorObject);
    }

    private HttpsURLConnection getHttpsURLConnection(String link, String post) throws IOException {
        URL url = new URL(link);
        HttpsURLConnection uc;
        uc = (HttpsURLConnection) url.openConnection();
        uc.setSSLSocketFactory(getAllowAllFactory());
        uc.setReadTimeout(20000);
        uc.setConnectTimeout(20000);
        uc.setRequestMethod(post);
        uc.addRequestProperty("User-Agent",
                "FKG-" + KFG.VERSION);
        uc.setRequestProperty("Content-Type", "application/json; utf-8");
        uc.setRequestProperty("Accept", "application/json");
        return uc;
    }

    public void sendGetRequest(final String path, final String params, final ResponseCallback callback) {
        new Thread(() -> {
            try {

                HttpsURLConnection con = getHttpsURLConnection(buildUrl(path, params),"GET");

                int status = con.getResponseCode();
                if(status != 200) throw new HttpResponseException(status, con.getResponseMessage());

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) content.append(inputLine);
                in.close();

                con.disconnect();
                callback.onResponse(content.toString());

            } catch(IOException e) { callback.onError(e); }
        }).start();
    }

    public void sendPostRequest(final String path, final String params, final String body, final ResponseCallback callback) {
        new Thread(() -> {
            try {
                HttpsURLConnection con = getHttpsURLConnection(buildUrl(path, params),"POST");

//                con.setSSLSocketFactory(getAllowAllFactory());
//                con.setRequestMethod("POST");
                con.setDoOutput(true);  // To send data in the request body
//                con.setRequestProperty("Content-Type", "application/json");
//                con.setConnectTimeout(20000);
//                con.setReadTimeout(20000);

                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = body.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int status = con.getResponseCode();
                if(status != 200) throw new HttpResponseException(status, con.getResponseMessage());

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) content.append(inputLine);
                in.close();

                con.disconnect();
                callback.onResponse(content.toString());

            } catch(IOException e) { callback.onError(e); }
        }).start();
    }

    public String buildUrl(String path, String params) throws MalformedURLException {
        String key = "key";
        String uuid = Minecraft.getMinecraft().thePlayer == null ? Minecraft.getMinecraft().getSession().getPlayerID()
                : Minecraft.getMinecraft().thePlayer.getUniqueID().toString().replaceAll("-", "");

        return baseurl + path + "?" + params;
    }

    public interface ResponseCallback {
        void onResponse(String a);
        void onError(Exception e);
    }
}
