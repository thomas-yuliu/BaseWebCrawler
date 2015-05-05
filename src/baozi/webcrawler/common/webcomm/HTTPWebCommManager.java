package baozi.webcrawler.common.webcomm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.entry.InstanceFactory;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.BaseWebPage;
import baozi.webcrawler.common.metainfo.HtmlStringWebPage;

public class HTTPWebCommManager implements WebCommManager {
  private LogManager logger = new LogManager(HTTPWebCommManager.class);

  @Override
  public BaseWebPage fetchPage(BaseURL url) {
    InputStream is = null;
    BufferedReader br;
    String lineItr;
    StringBuffer pageBuffer = new StringBuffer();
    BaseWebPage result = null;
    
    try {
        HttpURLConnection httpcon = (HttpURLConnection) url.getUrl().openConnection();
        httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");
        is = httpcon.getInputStream();
        //is = url.getUrl().openStream();  // throws an IOException
        br = new BufferedReader(new InputStreamReader(is));
        logger.logInfo("\nFetching urls on the page: " + url.getUrl().toString() + "\n");
        
        while ((lineItr = br.readLine()) != null) {
           logger.logDebug("page html: " + lineItr);
           pageBuffer.append(lineItr);
        }
        result = InstanceFactory.getOneWebPage();
    } catch (MalformedURLException mue) {
         logger.logError(mue.toString());
    } catch (IOException ioe) {
         logger.logError(ioe.toString());
    } finally {
        try {
            if (is != null) is.close();
        } catch (IOException ioe) {
          logger.logError(ioe.toString());
        }
    }
    return result;
  }
}
