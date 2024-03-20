package sio.tp5s2.Services;

import sio.tp5s2.Model.Categorie;
import sio.tp5s2.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesCategorie {
    //permet de recuperer la connection a la base de donnée
    private Connection cnx;
    //permet de decrire la rqt sql
    private PreparedStatement ps;
    //permet de recupere le resultat de la rqt : jeu d'enregistrment
    private ResultSet rs;

    public ServicesCategorie() {
        cnx= ConnexionBDD.getCnx();
    }

    public ArrayList<Categorie> getAllCategorie() throws SQLException {
        ArrayList<Categorie> lesCategories = new ArrayList<>();

        ps = cnx.prepareStatement("SELECT categorie.idCateg, categorie.nomCateg\n" +
                "FROM categorie");
        rs=ps.executeQuery();
        //tant qu'il ya des ligne dans le resultSet
        while (rs.next()){
            Categorie categorie = new Categorie(rs.getInt(1),rs.getString(2));
            lesCategories.add(categorie);
        }
        return lesCategories;
    }
    public ArrayList<String>getAllNomCategories() throws SQLException {
        ArrayList<String> lesNoms = new ArrayList<>();
        ps= cnx.prepareStatement("SELECT nomCateg from categorie");
        rs= ps.executeQuery();
        while (rs.next()){
            lesNoms.add(rs.getString(1));
        }
        return lesNoms;
    }
    public int getNumeroCategorieByNom(String nomCateg) throws SQLException {
        int idCateg = 0;
        ps = cnx.prepareStatement("select idCateg from categorie where nomCateg = ?");
        ps.setString(1, nomCateg);
        rs = ps.executeQuery();
        rs.next();
        idCateg = rs.getInt(1);
        return idCateg;
    }

}
