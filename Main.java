import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    private static HttpURLConnection connection;

    public static void main(String[] args) {

        String xsrfToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBsaWNhdGlvbklkIjoiMTAzIiw" +
                "idGVuYW50SWQiOiI3NSIsImhvc3RBZGRyZXNzIjoiMTAuMjMzLjk3LjAsMTAuMjMzLjExNi4xMSIsIm" +
                "FnZW50IjoiUG9zdG1hblJ1bnRpbWUvNy4yOC4xIiwiY29uY3VycmVudENvbm5lY3Rpb24iOiJUcnV" +
                "lIiwiaXNTeXN0ZW1Pd25lciI6IkZhbHNlIiwiZXh0ZXJuYWxDbGFpbXMiOiJ7fSIsIlVzZXJJZCI6Ij" +
                "I0NSIsIkV4cGlyYXRpb25EYXRlIjoiMTYyNzQ5MjQxMzc0Mi45OSJ9.AzFlYJRVavnEgcs6lQQylA0Pp" +
                "nrQKMVumis7z10RBPzY-bvenU74-NdF9KPvngYk9eEbEj1_yUzA-ID9lj0Yab4WKyu4DhBUCxvrnlq8" +
                "t-_7IuSoATSXLHoJHcIC5dO_IYM8wqlo3wrnVVisk2l5B2wQsfxX5RsN33uFx_XgJBU";

        //method 1 : java.net.HttpUrlConnection

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL("https://iotapi.skywaveiot.com/v1/vendor/apps");
            connection = (HttpURLConnection) url.openConnection();

            //request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("x-xsrf-token", xsrfToken);

            int status = connection.getResponseCode();
            //System.out.println(status);

            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line + "\n");
                }
                reader.close();
            }

            System.out.println(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }

    }

}
