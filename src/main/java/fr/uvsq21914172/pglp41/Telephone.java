package fr.uvsq21914172.pglp41;

public class Telephone {
  private String num;
  private String extension;

  public Telephone(String extension, String num) {
    this.extension = extension;
    this.num = num;
  }

  public String getNum() {
    return num;
  }

  public String getExtension() {
    return extension;
  }
}
