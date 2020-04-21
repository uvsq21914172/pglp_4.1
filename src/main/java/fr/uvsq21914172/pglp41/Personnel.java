package fr.uvsq21914172.pglp41;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Personnel implements Groupable {
  private String nom;
  private String prenom;
  private LocalDate dateDeNaissance;

  private List<String> titres;

  private List<Telephone> numbers;

  public static class Builder {
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;

    private List<String> titres = new ArrayList<String>();

    private List<Telephone> numbers = new ArrayList<Telephone>();

    /***
     * Builder.
     * @param nom Nom.
     * @param prenom Prenom.
     * @param dateDeNaissance Date de Naissance.
     */
    public Builder(String nom, String prenom, LocalDate dateDeNaissance) {
      this.nom = nom;
      this.prenom = prenom;
      this.dateDeNaissance = dateDeNaissance;
    }

    public Builder addNumber(Telephone number) {
      numbers.add(number);
      return this;
    }

    public Builder addTitres(String titre) {
      titres.add(titre);
      return this;
    }

    public Personnel build() {
      return new Personnel(nom, prenom, dateDeNaissance, numbers, titres);
    }
  }

  private Personnel(String nom, String prenom, LocalDate dateDeNaissance, List<Telephone> numbers,
      List<String> titres) {
    this.nom = nom;
    this.prenom = prenom;
    this.dateDeNaissance = dateDeNaissance;
    this.numbers = numbers;
    this.titres = titres;
  }

  /*
   * public String display() { return display(0); }
   * 
   * public String display(int level) { String space = ""; for (int i = 0; i < level; i++) space +=
   * "|"; return space + nom + " " + prenom + "," + dateDeNaissance.toString(); }
   */

  public List<String> getTitres() {
    return titres;
  }

  public List<Telephone> getNumbers() {
    return numbers;
  }

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getName() {
    return nom + prenom;
  }

  public LocalDate getDateDeNaissance() {
    return dateDeNaissance;
  }
}
