package baozi.webcralwer.common.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class LogManager {
  private Logger logger;
  private String className;

  public LogManager(Class callingClass) {
    // this.logger = LoggerFactory.getLogger(className);
    this.className = callingClass.getName();
  }

  public void logError(String msg) {
    if (shouldThisClassLog(className)) {
      System.out.println(msg);
    }
  }

  public void logInfo(String msg) {
    if (shouldThisClassLog(className)) {
      System.out.println(msg);
    }
  }

  public void logDebug(String msg) {
    if (shouldThisClassLog(className)) {
      System.out.println(msg);
    }
  }

  private static Set<String> loggingClasses = new HashSet<>(Arrays.asList(
      "baozi.webcrawler.common.urlidentifier.URLIdentifier",
      "baozi.webcrawler.common.urlidentifier.UrlIdentifierTest",
      //"baozi.webcrawler.common.urlidentifier.JsoupBasedURLIdentifier", 
      "baozi.webcrawler.common.entry.ConfigLoader",
      "baozi.webcrawler.common.metainfo.BaseURL",
      "baozi.webcrawler.common.urlfilter.PostExpansionFilterEnforcer", 
      //"baozi.webcrawler.common.urlfilter.FilterEnforcer",
      //"baozi.webcrawler.common.urlfilter.InMemroySeenUrlFilter",
      //"baozi.webcrawler.common.urlfilter.UrlDepthFilter",
      "baozi.webcrawler.common.urlfilter.FileExtensionFilter",
      "baozi.webcrawler.offerpage.analyzer.JavaPatternBasedOfferPageAnalyzer", 
      "baozi.webcrawler.offerpage.analyzer.JsoupBasedOfferPageAnalyzer", 
      "baozi.webcrawler.common.workflow.WorkflowManager", 
      "baozi.webcrawler.common.webcomm.JsoupWebCommManager", 
      "baozi.webcrawler.common.webcomm.HTTPWebCommManager", 
      "baozi.webcrawler.common.queue.InMemorySingleProcessBasicQueue",
      "EOF"));
  
  private static boolean shouldThisClassLog(String className) {
    if (loggingClasses.contains(className)) {
      return true;
    } else {
      //return false;
      return true;
    }
  }
}
