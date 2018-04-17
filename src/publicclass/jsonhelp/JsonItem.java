package publicclass.jsonhelp;

public class JsonItem {
  private String joName;
  private String[] items;

  public JsonItem(String joName, String[] items) {
    this.joName = joName;
    this.items = items;
  }

  public String getJoName() {
    return joName;
  }

  public void setJoName(String joName) {
    this.joName = joName;
  }

  public String[] getItems() {
    return items;
  }

  public void setItems(String[] items) {
    this.items = items;
  }
}
