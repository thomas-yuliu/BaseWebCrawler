package baozi.webcrawler.common.urlidentifier;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.entry.InstanceFactory;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.BaseWebPage;

public class UrlIdentifierTest {
  LogManager lm = new LogManager(UrlIdentifierTest.class);
  private URLIdentifier urlIdentifier = InstanceFactory.getURLIdentifier();
  
  private List<BaseURL> useUrlIdentifier(String html){
    BaseWebPage page = InstanceFactory.getOneWebPage();
    BaseURL url = null;
    try {
      url = new BaseURL(new URL("http://www.somebaseURL.com"), page);
    } catch (MalformedURLException e) {
      fail("MalformedURLException: " + e.toString());
    }
    return urlIdentifier.extractUrls(url);
  }

  @Test
  public void testNoLinks() {
    String html = "random html";
    List<BaseURL> result = useUrlIdentifier(html);
    assert result.isEmpty();
  }

  @Test
  public void testContainAHrefLink() {
    String html = "random html <a href=\"https://fullURL.com\">google</a>";
    List<BaseURL> result = useUrlIdentifier(html);
    lm.logDebug("result: " + result.get(0).getUrl().toString());
    assert result.size() == 1;
    assert result.get(0).getUrl().toString().contains("http://www.google.com");
  }

  @Test
  public void testContainQuestionMarkLink() {
    String html = "random html <a href=\"https://fullURL.com?some/param\">google</a>";
    List<BaseURL> result = useUrlIdentifier(html);
    assert 0 == 1;
    lm.logDebug("result: " + result.get(0).getUrl().toString());
    assert result.size() == 1;
    assert result.get(0).getUrl().toString().contains("http://www.google.com");
  }

  //@Test
  public void testContainALinkLink() {
    String html = "random html <link href=\"http://i.cdn.turner.com/cnn/.e/img/3.0/global/misc/apple-touch-icon.png\" rel=\"apple-touch-icon\" type=\"image/png\"/>";
    List<BaseURL> result = useUrlIdentifier(html);
    lm.logDebug("result size: " + result.size());
    assert result.size() == 1;
    lm.logDebug("result: " + result.get(0).getUrl().toString());
    assert result.get(0).getUrl().toString().contains("http://i.cdn.turner.com/cnn/.e/img/3.0/global/misc/apple-touch-icon.png");
  }

  @Test
  public void testContainAReference() {
    String html = "random html <a href=\"/some/deeper/URL\">google</a>";
    List<BaseURL> result = useUrlIdentifier(html);
    assert result.isEmpty();
  }

}
