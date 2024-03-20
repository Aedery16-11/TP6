package sio.tp5s2;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sio.tp5s2.Model.Categorie;
import sio.tp5s2.Services.ServicesCategorie;
import sio.tp5s2.Services.ServicesJeu;
import sio.tp5s2.Tools.ConnexionBDD;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private ConnexionBDD maCnx;
    private ServicesJeu servicesJeu;
    private ServicesCategorie servicesCategorie;
    @FXML
    private TableColumn tcNumeroCategorie;
    @FXML
    private TableView<Categorie> tvCategorie;
    @FXML
    private TableColumn tcLibelleCategorie;
    @FXML
    private TableView tvJeux;
    @FXML
    private TableColumn tcNomJeu;
    @FXML
    private TableColumn tcNumeroJeu;
    @FXML
    private TextField txtImage;
    @FXML
    private ComboBox cboCategorie;
    @FXML
    private TextField txtNumero;
    @FXML
    private Button btnInserer;
    @FXML
    private TextField txtNom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            maCnx=new ConnexionBDD();
            servicesCategorie = new ServicesCategorie();
            servicesJeu=new ServicesJeu();

            tcNumeroCategorie.setCellValueFactory(new PropertyValueFactory<>("numCategorie"));
            tcLibelleCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));

            tcNumeroJeu.setCellValueFactory(new PropertyValueFactory<>("idJeu"));
            tcNomJeu.setCellValueFactory(new PropertyValueFactory<>("nomJeu"));

            txtNumero.setText(String.valueOf(servicesJeu.getLastNumJeu()));
            cboCategorie.setItems(FXCollections.observableArrayList(servicesCategorie.getAllNomCategories()));

            tvCategorie.setItems(FXCollections.observableArrayList(servicesCategorie.getAllCategorie()));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void tvCategorieClicked(Event event) throws SQLException {
       int  numCateg =tvCategorie.getSelectionModel().getSelectedItem().getNumCategorie();
       tvJeux.setItems(FXCollections.observableArrayList(servicesJeu.getAllJeuxById(numCateg)));
    }

    @FXML
    public void btnInsererClicked(Event event) throws SQLException {
        int idCateg = servicesCategorie.getNumeroCategorieByNom(cboCategorie.getSelectionModel().getSelectedItem().toString());
        servicesJeu.insererJeu(Integer.parseInt(txtNumero.getText()), txtNom.getText(), txtImage.getText(), idCateg);
        txtNom.setText("");
        txtImage.setText("");
        txtNumero.setText(String.valueOf(servicesJeu.getLastNumJeu()));
    }
}