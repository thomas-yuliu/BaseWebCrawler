package baozi.webcrawler.common.urlidentifier;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseRawUrlsOnThePage;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.BaseWebPage;

public class UrlIdentifierTest {
  LogManager lm = new LogManager(UrlIdentifierTest.class);
  private URLIdentifier urlIdentifier = new URLIdentifier();
  
  private BaseRawUrlsOnThePage useUrlIdentifier(String html){
    BaseWebPage page = new BaseWebPage(html);
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
    BaseRawUrlsOnThePage result = useUrlIdentifier(html);
    assert result.getRawUrls().isEmpty();
  }

  @Test
  public void testContainAHrefLink() {
    String html = "random html <a href=\"https://fullURL.com\">google</a>";
    BaseRawUrlsOnThePage result = useUrlIdentifier(html);
    lm.logDebug("result: " + result.getRawUrls().get(0).getUrl().toString());
    assert result.getRawUrls().size() == 1;
    assert result.getRawUrls().get(0).getUrl().toString().contains("http://www.google.com");
  }

  @Test
  public void testContainQuestionMarkLink() {
    String html = "random html <a href=\"https://fullURL.com?some/param\">google</a>";
    BaseRawUrlsOnThePage result = useUrlIdentifier(html);
    assert 0 == 1;
    lm.logDebug("result: " + result.getRawUrls().get(0).getUrl().toString());
    assert result.getRawUrls().size() == 1;
    assert result.getRawUrls().get(0).getUrl().toString().contains("http://www.google.com");
  }

  //@Test
  public void testContainALinkLink() {
    String html = "random html <link href=\"http://i.cdn.turner.com/cnn/.e/img/3.0/global/misc/apple-touch-icon.png\" rel=\"apple-touch-icon\" type=\"image/png\"/>";
    BaseRawUrlsOnThePage result = useUrlIdentifier(html);
    lm.logDebug("result size: " + result.getRawUrls().size());
    assert result.getRawUrls().size() == 1;
    lm.logDebug("result: " + result.getRawUrls().get(0).getUrl().toString());
    assert result.getRawUrls().get(0).getUrl().toString().contains("http://i.cdn.turner.com/cnn/.e/img/3.0/global/misc/apple-touch-icon.png");
  }

  @Test
  public void testContainAReference() {
    String html = "random html <a href=\"/some/deeper/URL\">google</a>";
    BaseRawUrlsOnThePage result = useUrlIdentifier(html);
    assert result.getRawUrls().isEmpty();
  }

}
