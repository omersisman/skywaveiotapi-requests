import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

//Sending http post request with json body and getting json response
public class Main3 {

    public static void main(String[] args) {

        Main3.Post_Json();

    }

    public static void Post_Json(){

        String query_url = "https://iotapi.skywaveiot.com/v1/login/ENT";
        String json = "{ \"username\" : \"osisman\", \"password\" : \"istGms2934.\"}";

        try {

            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
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
