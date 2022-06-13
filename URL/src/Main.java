import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void urlTest() {
        try {
            URL url = new URL("http://cookiecentral.com/");
            InputStream in = url.openStream();
            OutputStream out = new FileOutputStream("site.html");
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (MalformedURLException e) {
            System.out.println("Not valid URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void urlConnectionTest() {
        try {
            URL url = new URL("http://www.cookiecentral.com");
            URLConnection uc = url.openConnection();
            InputStream in = uc.getInputStream();
            String header;
            int j = 1;
            while ((header = uc.getHeaderField(j)) != null) {
                System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
                j++;
            }

            in.close();
            System.out.println();
        } catch (MalformedURLException e) {
            System.out.println("Not valid URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void httpURLTest() {
        try {
            URL url = new URL("http://www.cookiecentral.com");
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            uc.setUseCaches(false);
            uc.setRequestMethod("GET");
            uc.setRequestProperty("User-Agent", "Mozilla/5.0");
            int response_code = uc.getResponseCode();
            System.out.println("GET Response Code: " + response_code);
            if (response_code == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("site.html")));
                String s;
                StringBuffer response = new StringBuffer();
                while ((s = in.readLine()) != null) {
                    response.append(s);
                }
                out.write(response.toString());
                in.close();
                out.close();
            }
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        urlTest();
        urlConnectionTest();
        httpURLTest();
    }
}
