/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetgr1.smart.money;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author fehim
 */
public class FXMLController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private SplitPane splitePane;

    @FXML
    private TableView<Ressource> tableau;

    @FXML
    private Label userName;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;

    private ArrayList<Ressource> data = new ArrayList();

    /**
     * Initializes the controller class.
     */
    ResultSet rs;
    Connection con;
    PreparedStatement pst;
    @FXML
    private TableColumn<Ressource, String> aideFamille;
    @FXML

    private TableColumn<Ressource, String> job;
    @FXML
    private TableColumn<Ressource, String> bource;
    @FXML
    private TableColumn<Ressource, String> autre;
    @FXML
    private TableColumn<Ressource, String> loisir;
    @FXML
    private TableColumn<Ressource, String> weekEnd;
    @FXML
    private TableColumn<Ressource, String> sante;
    @FXML
    private TableColumn<Ressource, String> transport;
    @FXML
    private TableColumn<Ressource, String> nourriture;
    @FXML
    private TableColumn<Ressource, String> revenueMois;
    @FXML
    private TableColumn<Ressource, String> depenceFixeMois;
    @FXML
    private TableColumn<Ressource, String> restVive;
    @FXML
    private TextField aideFamilleTF;
    @FXML
    private TextField santeTF;
    @FXML
    private TextField jobTF;
    @FXML
    private TextField bourceTF;
    @FXML
    private TextField autreTF;
    @FXML
    private TextField weekEndTF;
    @FXML
    private TextField loisirTF;
    @FXML
    private TextField transportTF;
    @FXML
    private TextField nourritureTF;
    @FXML
    private Label status;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        con = ConnectDB.connectDataB();
        try {
            pst = con.prepareStatement("SELECT * FROM IDETUDIANT WHERE USERNAME='" + FXMLDocumentController.user + "'");
            rs = pst.executeQuery();
            firstName.setText(rs.getString(1));
            lastName.setText(rs.getString(2));
            userName.setText(FXMLDocumentController.user);
            pst.close();
            rs.close();
            con.close();

        } catch (SQLException e) {
            affiMessError(e.getMessage());
        }
        aideFamille.setCellValueFactory(new PropertyValueFactory<Ressource, String>("aideFamille"));

        job.setCellValueFactory(new PropertyValueFactory<Ressource, String>("job"));

        bource.setCellValueFactory(new PropertyValueFactory<Ressource, String>("bource"));

        autre.setCellValueFactory(new PropertyValueFactory<Ressource, String>("autre"));

        nourriture.setCellValueFactory(new PropertyValueFactory<Ressource, String>("nourriture"));

        transport.setCellValueFactory(new PropertyValueFactory<Ressource, String>("transport"));

        loisir.setCellValueFactory(new PropertyValueFactory<Ressource, String>("loisir"));

        sante.setCellValueFactory(new PropertyValueFactory<Ressource, String>("sante"));

        weekEnd.setCellValueFactory(new PropertyValueFactory<Ressource, String>("weekEnd"));

        revenueMois.setCellValueFactory(new PropertyValueFactory<Ressource, String>("revenueMois"));

        restVive.setCellValueFactory(new PropertyValueFactory<Ressource, String>("restVive"));
        restVive.setMaxWidth(80);
        depenceFixeMois.setCellValueFactory(new PropertyValueFactory<Ressource, String>("depenceFixeMois"));

        connectCompte();
    }

    @FXML
    private void closeExit(MouseEvent event) {
        close.setStyle("-fx-background-color:#1cb0");
    }

    @FXML
    private void closeEntered(MouseEvent event) {
        close.setStyle("-fx-background-color:red");
    }

    @FXML
    private void closeClick(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void logout(ActionEvent event) {
        try {

            Stage stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("InterfaceDeConnexion.fxml"));
            Scene scene = new Scene(root);

            
            stage.setScene(scene);

            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            ((Node) event.getSource()).getScene().getWindow().hide();

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void insert(ActionEvent event) {
        try {

            data.add(new Ressource(FXMLDocumentController.user, Double.valueOf(aideFamilleTF.getText()), Double.valueOf(jobTF.getText()),
                    Double.valueOf(bourceTF.getText()), Double.valueOf(autreTF.getText()),
                    Double.valueOf(loisirTF.getText()), Double.valueOf(weekEndTF.getText()),
                    Double.valueOf(santeTF.getText()), Double.valueOf(transportTF.getText()),
                    Double.valueOf(nourritureTF.getText())));
            tableau.setItems(FXCollections.observableArrayList(data));
            aideFamilleTF.clear();
            bourceTF.clear();
            jobTF.clear();
            autreTF.clear();
            santeTF.clear();
            loisirTF.clear();
            weekEndTF.clear();
            nourritureTF.clear();
            transportTF.clear();
        } catch (NumberFormatException e) {
            affiMessError(e.getMessage());
        }

    }

    @FXML
    
    private void delete(ActionEvent event) {
    // supp donne tableView
        try {
            con = ConnectDB.connectDataB();
            if (!data.isEmpty()) {
                if (data.size() <= 7) {

                    try {

                        pst = con.prepareStatement("DELETE  FROM DATA_ACCOUNT  WHERE USERNAME='" + FXMLDocumentController.user + "'"
                                + " AND WEEKEND='" + data.get(data.size() - 1).getWeekEnd() + "' AND LOISIR=' " + data.get(data.size() - 1).getLoisir() + "' AND SANTE='" + data.get(data.size() - 1).getSante() + "'");
                        pst.execute();
                        pst.close();
                        con.close();

                    } catch (SQLException e) {
                        affiMessError(e.getMessage());
                    }
                    data.remove((data.size() - 1));
                    tableau.setItems(FXCollections.observableList(data));
                } else {
                    VBox box = new VBox();
                    Button valide = new Button("valider");
                    Text text = new Text("Table taille   " + data.size());
                    Text text1 = new Text("nombre de cell a supprimer");
                    box.setPadding(new Insets(50, 50, 50, 50));
                    box.setSpacing(10);
                    Spinner<Integer> spine = new Spinner<Integer>(7, 15, 20);

                    spine.setEditable(true);
                    box.getChildren().addAll(text, text1, spine, valide);
                    valide.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            while (data.size() > 7) {

                                try {

                                    pst = con.prepareStatement("DELETE  FROM DATA_ACCOUNT  WHERE USERNAME='" + FXMLDocumentController.user + "'"
                                            + " AND WEEKEND='" + data.get(data.size() - 1).getWeekEnd() + "' AND LOISIR=' " + data.get(data.size() - 1).getLoisir() + "' AND SANTE='" + data.get(data.size() - 1).getSante() + "'");
                                    pst.execute();
                                    pst.close();
                                    con.close();
                                } catch (SQLException e) {
                                    affiMessError(e.getMessage());
                                }
                                data.remove((data.size() - 1));
                                tableau.setItems(FXCollections.observableList(data));

                            }

                        }
                    });
                    Stage window = new Stage();
                    Scene scene = new Scene(box, 250, 200);
                    window.setScene(scene);
                    window.showAndWait();
                }

            } else {

                affiMessError("Table Vide");
            }
        } catch (IllegalStateException e) {
            affiMessError(e.getMessage());
        }
    }

    public void connectCompte() {
   // chargement de donne de database vers compte
        try {
            con = ConnectDB.connectDataB(); // connecter a database
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM DATA_ACCOUNT WHERE USERNAME='" + FXMLDocumentController.user + "'");
            Double somme = 0.0;
            while (rs.next()) {

                data.add(new Ressource(rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10)));
                // chargement donnée utilisateur
                somme = somme + rs.getDouble(11);
                // etat du rest de l'etudiant
                if (somme > 0) {
                    status.setText("GOOD");
                } else {
                    if (somme < 0) {
                        status.setText("BAD");

                    } else {
                        status.setText("EQUILIBRATE");

                    }

                }
                
            }
            tableau.setItems(FXCollections.observableList(data)); // ajout donne au table view avec FXCollection.observableList ()
                                                                 // qui retourne une observableList
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            affiMessError(e.getMessage());
        }
    }

    // methode pour afficher erreur
    private void affiMessError(String e) {
        AnchorPane pane = new AnchorPane();
        Stage window = new Stage();
        Label error = new Label(e);
        error.setLayoutX(50);
        error.setLayoutY(50);
        pane.getChildren().add(error);
        Scene sceneErro = new Scene(pane, 200, 200);
        window.setScene(sceneErro);
        window.show();
    }

}
