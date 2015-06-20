package baozi.webcrawler.common.entry;

import baozi.webcrawler.common.analyzer.Analyzer;
import baozi.webcrawler.common.metainfo.BaseWebPage;
import baozi.webcrawler.common.metainfo.JsoupDocWebPage;
import baozi.webcrawler.common.queue.InMemorySingleProcessBasicQueue;
import baozi.webcrawler.common.queue.URLQueue;
import baozi.webcrawler.common.urlfilter.PostExpansionFilterEnforcer;
import baozi.webcrawler.common.urlfilter.PreExpansionFilterEnforcer;
import baozi.webcrawler.common.urlidentifier.JsoupBasedURLIdentifier;
import baozi.webcrawler.common.urlidentifier.URLIdentifier;
import baozi.webcrawler.common.webcomm.JsoupWebCommManager;
import baozi.webcrawler.common.webcomm.WebCommManager;
import baozi.webcrawler.offerpage.analyzer.JsoupBasedOfferPageAnalyzer;

public class InstanceFactory {

  private static URLQueue nextQueue = new InMemorySingleProcessBasicQueue();
  private static WebCommManager webcomm = new JsoupWebCommManager();
  private static Analyzer analyzer = new JsoupBasedOfferPageAnalyzer();
  private static URLIdentifier urlIdentifier = new JsoupBasedURLIdentifier();
  private static PostExpansionFilterEnforcer postExpansionfilterEnforcer = new PostExpansionFilterEnforcer();
  private static PreExpansionFilterEnforcer preExpansionfilterEnforcer = new PreExpansionFilterEnforcer();
  
  public static URLQueue getNextURLQueueInstance(){
    return nextQueue;
  }
  public static WebCommManager getWebCommManager(){
    return webcomm;
  }
  public static Analyzer getAnalyzer(){
    return analyzer;
  }
  public static URLIdentifier getURLIdentifier(){
    return urlIdentifier;
  }
  public static PostExpansionFilterEnforcer getPostExpansionFilterEnforcer(){
    return postExpansionfilterEnforcer;
  }
  public static PreExpansionFilterEnforcer getPreExpansionFilterEnforcer(){
    return preExpansionfilterEnforcer;
  }
  public static BaseWebPage getOneWebPage(){
    return new JsoupDocWebPage();
  }
}
