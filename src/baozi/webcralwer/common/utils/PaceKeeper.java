package baozi.webcralwer.common.utils;

public class PaceKeeper {

  public static void pause(){
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
