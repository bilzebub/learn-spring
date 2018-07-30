package learn.spring.prof_prop_spel;

public class StringWrapper {
  private final String str;

  public StringWrapper(String str) {
    this.str = str;
  }

  @Override
  public String toString() {
    return str;
  }
}
