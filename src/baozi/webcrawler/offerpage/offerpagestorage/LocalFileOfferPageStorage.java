package baozi.webcrawler.offerpage.offerpagestorage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import baozi.webcrawler.common.metainfo.BaseURL;

public class LocalFileOfferPageStorage implements OfferPageStorage {

  // TODO to read from config.json
  private String basefilename = "/Users/yliu/offerpages-";
  private String filename;
  
  public LocalFileOfferPageStorage(){
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
      Date date = new Date();
      filename = basefilename + dateFormat.format(date) + ".txt";
  }

  @Override
  public void storeOfferPage(BaseURL url) {
    // TODO to keep outputstream open
    try {
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
      out.println(url.getUrl().toString() + ": " + url.getPageContent().getTitle());
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
