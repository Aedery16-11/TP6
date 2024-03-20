package sio.tp5s2.Model;

public class Jeu {
    private int idJeu;
    private String nomJeu;

    public Jeu(int idJeu, String nomJeu) {
        this.idJeu = idJeu;
        this.nomJeu = nomJeu;
    }

    public int getIdJeu() {
        return idJeu;
    }

    public String getNomJeu() {
        return nomJeu;
    }
}
