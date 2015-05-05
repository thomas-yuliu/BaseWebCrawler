package baozi.webcrawler.common.urlfilter;

import java.io.IOException;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;

public class ContentTypeFilter implements Filter {
  private LogManager logger = new LogManager(ContentTypeFilter.class);

  private Set<String> contentTypeToDrop = new HashSet<String>(Arrays.asList(
      "application/javascript", "application/json", "text/plain", "image/jpeg",
      "image/jpeg", "image/pjpeg", "image/pjpeg", "application/javascript", 
      "text/javascript", "image/gif", "application/x-pointplus", "text/css"));

  @Override
  public boolean filter(BaseURL url) {
    boolean result = true;
    URLConnection connection;
    try {
      connection = url.getUrl().openConnection();
      connection.connect();
      String contentType = connection.getContentType();
      logger.logDebug("content type: " + contentType);
      for(String type : contentTypeToDrop){
        if(contentType != null && contentType.contains(type)){
          result = false;
        }
      }
    } catch (IOException e) {
      logger.logError("exception: " + e.toString());
    }
    return result;
  }

}
