/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetgr1.smart.money;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author fehim
 */
public class FXMLDocumentController implements Initializable {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private Button singIn;
    @FXML
    private Button singUp;

    @FXML
    private Pane SingupFenetre;
    @FXML
    private Button close;
    @FXML
    private Pane SingUpWindows;
    @FXML
    private Pane SingInWindows;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private PasswordField psWordInc;
    @FXML
    private TextField userIncNmtF;

    @FXML
    private PasswordField passWord;
    @FXML
    private TextField userName;

    Connection con;
    static String user;
    @FXML
    private Label faileToConnect;
    @FXML
    private Label check;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        con = ConnectDB.connectDataB();

    }

    @FXML
    private void singUpAcc(MouseEvent event) {

        SingInWindows.setOpacity(0);
        SingInWindows.setLayoutY(500);
        SingUpWindows.setOpacity(1);

    }

    @FXML
    private void closeExit(MouseEvent event) {
        close.setStyle("-fx-background-color:#1cb0");

    }

    @FXML
    private void closeEnter(MouseEvent event) {
        close.setStyle("-fx-background-color:red");
    }

    @FXML
    private void closeClick(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void singInAcc(MouseEvent event) {
        SingInWindows.setOpacity(1);
        SingInWindows.setLayoutY(118);
        SingUpWindows.setOpacity(0);
    }

    @FXML
    private void ajouterCompte(ActionEvent event) throws IOException {
        try {
            pst = con.prepareStatement("SELECT * FROM IDETUDIANT WHERE USERNAME=?");
            pst.setString(1, userIncNmtF.getText());
            rs = pst.executeQuery();

            if (rs.next() == false && !userIncNmtF.getText().equals("") && !psWordInc.getText().equals("")
                    && !firstNameTF.getText().equals("") && !lastNameTF.getText().equals("")
                    && firstNameTF.getLength() <= 16 && lastNameTF.getLength() <= 16 && psWordInc.getLength() <= 7) {

                pst = con.prepareStatement("INSERT INTO IDETUDIANT(FIRSTNAME,LASTNAME,PASSWORD,USERNAME) VALUES(?,?,?,?)");
                pst.setString(1, firstNameTF.getText());
                pst.setString(2, lastNameTF.getText());
                pst.setString(3, psWordInc.getText());
                pst.setString(4, userIncNmtF.getText());
                user = userIncNmtF.getText();
                pst.execute();
                pst.close();
                rs.close();
                con.close();
                stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("Compte.fxml"));
                scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setResizable(false);
                stage.show();
                ((Node) event.getSource()).getScene().getWindow().hide();

            } else {

                if (userIncNmtF.getText().equals("") || psWordInc.getText().equals("")
                        || firstNameTF.getText().equals("") || lastNameTF.getText().equals("")) {
                    check.setText("empty sapce in filed ");
                } else {
                    if (firstNameTF.getLength() > 16) {
                        check.setText("first name most last 16");
                    } else {
                        if (lastNameTF.getLength() > 16) {
                            check.setText("last name most last 16");
                        } else {
                            if (psWordInc.getLength() > 7) {
                                check.setText("password lass then 7");
                            } else {
                                check.setText("username exist");
                            }
                        }
                    }
                }
        

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Connect(ActionEvent event) throws IOException {
        try {
            pst = con.prepareStatement("SELECT * FROM IDETUDIANT WHERE USERNAME=? AND PASSWORD=?");
            pst.setString(1, userName.getText());
            pst.setString(2, passWord.getText());
            rs = pst.executeQuery();
            user = userName.getText();
            if (rs.next() == true) {

                stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("Compte.fxml"));
                scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                ((Node) event.getSource()).getScene().getWindow().hide();
                pst.close();
                rs.close();
                con.close();
            } else {

                faileToConnect.setText("Faile to Connect");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
