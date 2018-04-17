package publicclass.jsonhelp;

public class JsonBean {
  private Object bean;
  private String beanName;

  public JsonBean(Object bean, String beanName) {
    this.bean = bean;
    this.beanName = beanName;
  }

  public Object getBean() {
    return bean;
  }


  public String getBeanName() {
    return beanName;
  }

}
