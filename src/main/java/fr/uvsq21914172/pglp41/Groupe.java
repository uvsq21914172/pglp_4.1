package fr.uvsq21914172.pglp41;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Groupe implements Groupable, Iterable<Groupable> {
  private String name;
  private List<Groupe> sousgroupes;
  private List<Personnel> membres;

  /***
   * Objet representant les différents groupes.
   * @param name Name.
   */
  public Groupe(String name) {
    this.name = name;
    sousgroupes = new ArrayList<Groupe>();
    membres = new ArrayList<Personnel>();
  }

  /***
   * Ajoute un personnel.
   * @param p Personnel.
   */
  public void add(Personnel p) {
    membres.add(p);
  }

  /***
   * Ajoute un sous groupe.
   * @param g SousGroupe.
   */
  public void add(Groupe g) {
    sousgroupes.add(g);
  }

  // public String display() {
  // return display(0);
  // }
  //
  // public String display(int level) {
  // String rep = "";
  // String space = "";
  // for(int i = 0; i < level; i++)
  // space += "|";
  // rep = space + name + ":\n";
  // for(int i = 0; i < membres.size(); i++) {
  // rep += membres.get(i).display(level + 1) + "\n";
  // }
  // return rep;
  // }

  private static class IteratorProfondeur implements Iterator<Groupable> {
    private Groupe current;
    private Stack<Groupe> iterated;
    int position = -1;

    /***
     * Iterateur suivant le parcours en profondeur.
     * @param iterated Groupe parcouru.
     */
    private IteratorProfondeur(Groupe iterated) {
      this.current = iterated;
      this.iterated = new Stack<Groupe>();
      for (Groupe g : current.sousgroupes) {
        this.iterated.push(g);
      }
    }

    public boolean hasNext() {
      if (position < current.membres.size() || !iterated.isEmpty()) {
        return true;
      }
      return false;
    }

    public Groupable next() {
      Groupable g = null;
      if (hasNext()) {
        if (position == -1) {
          g = current;
        } else {
          g = current.membres.get(position);
        }
        position++;
        if (!(position < current.membres.size()) && !iterated.isEmpty()) {
          current = iterated.pop();
          for (Groupe h : current.sousgroupes) {
            this.iterated.push(h);
          }
          position = -1;
        }
      }
      return g;
    }

  }

  private static class IteratorLargeur implements Iterator<Groupable> {
    private Groupe current;
    private ArrayList<Groupe> iterated;
    int position = -1;

    /***
     * Iterateur suivant le parcours en largeur.
     * @param iterated Groupe parcouru.
     */
    private IteratorLargeur(Groupe iterated) {
      this.current = iterated;
      this.iterated = new ArrayList<Groupe>();
      for (Groupe g : current.sousgroupes) {
        this.iterated.add(g);
      }
    }

    public boolean hasNext() {
      // TODO Auto-generated method stub
      if (position < current.membres.size() || !iterated.isEmpty()) {
        return true;
      }
      return false;
    }

    public Groupable next() {
      // TODO Auto-generated method stub
      Groupable g = null;
      if (hasNext()) {
        if (position == -1) {
          g = current;
        } else {
          g = current.membres.get(position);
        }
        position++;
        if (!(position < current.membres.size()) && !iterated.isEmpty()) {
          current = iterated.remove(0);
          for (Groupe h : current.sousgroupes) {
            this.iterated.add(h);
          }
          position = -1;
        }
      }
      return g;
    }

  }

  public Iterator<Groupable> iteratorProfondeur() {
    return new IteratorProfondeur(this);
  }

  public Iterator<Groupable> iteratorLargeur() {
    return new IteratorLargeur(this);
  }

  public Iterator<Groupable> iterator() {
    return new IteratorProfondeur(this);
  }

  public String getName() {
    return name;
  }
}
