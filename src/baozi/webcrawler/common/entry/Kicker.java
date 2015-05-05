package baozi.webcrawler.common.entry;
import baozi.webcrawler.common.workflow.WorkflowManager;

class Kicker {

  public void kick() {
    ConfigLoader configLoader = new ConfigLoader();
    configLoader.load();
    WorkflowManager workflow = new WorkflowManager();
    workflow.crawl();
  }
}
