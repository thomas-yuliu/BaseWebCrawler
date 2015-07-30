package baozi.webcrawler.offerpage.offerpagestorage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import scala.util.Random;
import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;

public class ParseOfferPageStorage implements OfferPageStorage {
  private LogManager logger = new LogManager(ParseOfferPageStorage.class);

  private final String USER_AGENT = "Mozilla/5.0";
  private final String app_id_tag = "X-Parse-Application-Id";
  private final String app_id = "tPOjE1BD0EgnNKv4gF5Gc3pTpqmMab7JwXCQ8pzC";
  private final String api_key = "lp35kiJuzDnbaZ7wCpAPlIVq0fcRtgzs2JK4ib6r";
  private final String api_key_tag = "X-Parse-REST-API-Key";
  private final String content_type_tag = "Content-Type";
  private final String content_type_json = "application/json";

  public ParseOfferPageStorage(){
  }

  @Override
  public void storeOfferPage(BaseURL url) {
    try {
      URL obj = new URL("https://api.parse.com/1/classes/Offer");
      HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

      // add reuqest header
      con.setRequestMethod("POST");
      con.setRequestProperty(app_id_tag, app_id);
      con.setRequestProperty(api_key_tag, api_key);
      con.setRequestProperty(content_type_tag, content_type_json);
      
      String company = null;
      String[] companies = {"Google", "G家","Facebook", "F家", "Amazon", "亚麻", "亚马逊", "Uber", "Zenefits","Airbnb","Slack", "Microsoft", "微软", "Linkedin", "L家","Twitter", "T家"};
      for(String itr : companies){
        if(url.getPageContent().getPageHtml().contains(itr)){
          company = itr;
          break;
        }
      }
      String compensation = null;
      int pos = url.getPageContent().getPageHtml().indexOf("Base");
      if(pos >= 0){
        compensation = url.getPageContent().getPageHtml().substring(pos, pos + 10);
      } else {
      pos = url.getPageContent().getPageHtml().indexOf("base");
      if(pos >= 0){
        compensation = url.getPageContent().getPageHtml().substring(pos, pos + 10);
      }
      }
      
      String objToPut = "{\"companyName\": \"" + company + "\", \"compensation\": \"" + compensation + "\", \"title\":\"Software Engineer\", \"source\":\""+url.getUrl().toString()+"\"}";

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
  }
}
