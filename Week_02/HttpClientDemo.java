import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

public class HttpClientDemo {
    public static void main(String[] args) {
        String s = get("http://localhost:8801");
        System.out.println(s);
    }
    public static String get(String url) {
        HttpGet method = new HttpGet(url);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String result = "";
        try {
            HttpResponse response = httpClient.execute(method);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer resBuffer = new StringBuffer();
            String resTemp = "";
            while((resTemp = br.readLine()) != null){
                resBuffer.append(resTemp);
            }
            result = resBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
