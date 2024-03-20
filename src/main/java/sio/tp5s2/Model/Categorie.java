package sio.tp5s2.Model;

public class Categorie {
    private int numCategorie;
    private String nomCategorie;

    public Categorie(int numCategorie, String nomCategorie) {
        this.numCategorie = numCategorie;
        this.nomCategorie = nomCategorie;
    }

    public int getNumCategorie() {
        return numCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }
}
