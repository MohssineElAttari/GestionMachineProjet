/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Machine;
import classe.Profil;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.ProfilService;

/**
 * FXML Controller class
 *
 * @author imadeddine
 */
public class ProfilController implements Initializable {

    private ProfilService ps = new ProfilService();
    ObservableList<Profil> profilList = FXCollections.observableArrayList();

    private static int index;

    @FXML
    private TextField code;

    @FXML
    private TextField libelle;

    @FXML
    private ImageView logo;

    @FXML
    private TableView<Profil> profils;

    @FXML
    private TableColumn<Profil, String> cCode;

    @FXML
    private TableColumn<Profil, String> cLibelle;

    @FXML
    private void saveAction(ActionEvent e) {
        String c = code.getText().toString();
        String l = libelle.getText().toString();

        ps.create(new Profil(c, l));
        load();
        clear();
    }

    @FXML
    private void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Vous vous vraiment supprimer cette machine?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ps.delete(ps.findById(index));
            profilList.clear();
            load();
            clear();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        

    }

    public void clear() {
        code.setText("");
        libelle.setText("");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo.setImage(new Image("images/network.png", 160, 160, false, true));
        load();
        profils.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                TablePosition tablePosition = (TablePosition) profils.getSelectionModel().getSelectedCells().get(0);
                int row = tablePosition.getRow();
                Profil item = profils.getItems().get(row);
                code.setText(item.getCode());
                libelle.setText(item.getLibelle());
                index = item.getId();
                load();

            }

        });
    }

    public void load() {
        profilList.clear();
        cCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        cLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        for (Profil p : ps.findAll()) {
            profilList.add(new Profil(p.getId(),p.getCode(), p.getLibelle()));
        }

        profils.setItems(profilList);
    }

}
