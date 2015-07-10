package baozi.webcrawler.offerpage.analyzer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Test;

import baozi.webcralwer.common.utils.LogManager;

public class PostToParseTest {
  private LogManager logger = new LogManager(PostToParseTest.class);

  private final String USER_AGENT = "Mozilla/5.0";
  private final String app_id_tag = "X-Parse-Application-Id";
  private final String app_id = "tPOjE1BD0EgnNKv4gF5Gc3pTpqmMab7JwXCQ8pzC";
  private final String api_key = "lp35kiJuzDnbaZ7wCpAPlIVq0fcRtgzs2JK4ib6r";
  private final String api_key_tag = "X-Parse-REST-API-Key";
  private final String content_type_tag = "Content-Type";
  private final String content_type_json = "application/json";

  @Test
  public void postAnObjectREST() {
    try {
      String url = "https://api.parse.com/1/classes/Offer";
      URL obj = new URL(url);
      HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

      // add reuqest header
      con.setRequestMethod("POST");
      con.setRequestProperty(app_id_tag, app_id);
      con.setRequestProperty(api_key_tag, api_key);
      con.setRequestProperty(content_type_tag, content_type_json);

      String objToPut = "{\"compensation\": \"Base: 130k\", \"title\":\"Software Developer\"}";

      // Send post request
      con.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(con.getOutputStream());
      wr.writeBytes(objToPut);
      wr.flush();
      wr.close();

      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'POST' request to URL : " + url);
      System.out.println("Post parameters : " + objToPut);
      System.out.println("Response Code : " + responseCode);

      BufferedReader in = new BufferedReader(new InputStreamReader(
          con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      // print result
      System.out.println(response.toString());
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ProtocolException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      String url = "https://api.parse.com/1/classes/Offer";
      URL obj = new URL(url);
      HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

      // add reuqest header
      con.setRequestMethod("GET");
      con.setRequestProperty(app_id_tag, app_id);
      con.setRequestProperty(api_key_tag, api_key);
      con.setRequestProperty(content_type_tag, content_type_json);

      if (con.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
            + con.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader(
          (con.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      con.disconnect();

    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ProtocolException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
