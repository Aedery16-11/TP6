package sio.tp5s2.Services;

import sio.tp5s2.Model.Jeu;
import sio.tp5s2.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesJeu {
        //permet de recuperer la connection a la base de donnée
        private Connection cnx;
        //permet de decrire la rqt sql
        private PreparedStatement ps;
        //permet de recupere le resultat de la rqt : jeu d'enregistrment
        private ResultSet rs;

        public ServicesJeu() {
            cnx= ConnexionBDD.getCnx();
        }
    public ArrayList<Jeu> getAllJeuxById(int idCateg) throws SQLException {
        ArrayList<Jeu> lesJeux = new ArrayList<>();
        ps = cnx.prepareStatement("SELECT jeux.idJeux, jeux.nomJeux\n" +
                "FROM jeux\n" +
                "WHERE jeux.numCateg = ?");
        ps.setInt(1,idCateg);
        rs = ps.executeQuery();
        while (rs.next()){
            Jeu jeu = new Jeu(rs.getInt(1), rs.getString(2));
            lesJeux.add(jeu);
        }
        rs.close();
        ps.close();

        return lesJeux;
    }
    public int getLastNumJeu() throws SQLException {
        int lastNum=0;
        ps= cnx.prepareStatement("SELECT max(jeux.idJeux)+1\n" +
                "FROM jeux");
        rs = ps.executeQuery();
        rs.next();
        lastNum= rs.getInt(1);
        rs.close();
        ps.close();
        return lastNum;
    }
    public void insererJeu(int idJeu, String nomJeu, String imageJeu, int numCateg) throws SQLException {
        ps = cnx.prepareStatement("insert into jeux values (?, ?, ?, ?)");
        ps.setInt(1, idJeu);
        ps.setString(2, nomJeu);
        ps.setString(3, imageJeu);
        ps.setInt(4, numCateg);
        ps.executeUpdate(); //on fait un executeQuery quand on fait un select uniquement là y'a de select
    }
}
