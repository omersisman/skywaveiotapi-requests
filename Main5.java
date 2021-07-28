import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main5 {

    public static void main(String[] args) {

        Main5.Post_Json();

    }

    public static void Post_Json(){

        String query_url = "https://iotapi.skywaveiot.com/v1/vendor/device";
        String json = "{\n" +
                "\"brand\": \"deneme deneme\",\n" +
                "\"customId\": \"sn:2929292929292929\",\n" +
                "\"deviceProfileId\": 53,\n" +
                "\"locationId\": 1,\n" +
                "\"model\": \"deneme model\",\n" +
                "\"name\": \"deneme\",\n" +
                "\"servedAppId\": 6,\n" +
                "\"vendorId\": 1,\n" +
                "\"status\": \"active\"\n" +
                "}";
        String xsrfToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBsaWNhdGlvbklkIjoiMTAzIiw" +
                "idGVuYW50SWQiOiI3NSIsImhvc3RBZGRyZXNzIjoiMTAuMjMzLjk3LjAsMTAuMjMzLjExNi4xMSIsIm" +
                "FnZW50IjoiUG9zdG1hblJ1bnRpbWUvNy4yOC4xIiwiY29uY3VycmVudENvbm5lY3Rpb24iOiJUcnV" +
                "lIiwiaXNTeXN0ZW1Pd25lciI6IkZhbHNlIiwiZXh0ZXJuYWxDbGFpbXMiOiJ7fSIsIlVzZXJJZCI6Ij" +
                "I0NSIsIkV4cGlyYXRpb25EYXRlIjoiMTYyNzQ5MjQxMzc0Mi45OSJ9.AzFlYJRVavnEgcs6lQQylA0Pp" +
                "nrQKMVumis7z10RBPzY-bvenU74-NdF9KPvngYk9eEbEj1_yUzA-ID9lj0Yab4WKyu4DhBUCxvrnlq8" +
                "t-_7IuSoATSXLHoJHcIC5dO_IYM8wqlo3wrnVVisk2l5B2wQsfxX5RsN33uFx_XgJBU";

        try {

            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("x-xsrf-token", xsrfToken);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
            System.out.println("result after Reading JSON Response");
            JSONObject myResponse = new JSONObject(result);
            System.out.println("tokenExpirationDate- "+myResponse.getString("tokenExpirationDate"));
            System.out.println("token- "+myResponse.getString("token"));
            in.close();
            conn.disconnect();

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
