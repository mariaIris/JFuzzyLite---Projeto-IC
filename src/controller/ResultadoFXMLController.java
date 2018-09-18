/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MariaI
 */
public class ResultadoFXMLController implements Initializable {

    @FXML
    private Label exelente;
    @FXML
    private Label muitoBom;
    @FXML
    private Label bom;
    @FXML
    private Label regular;
    @FXML
    private Label preocupante;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       float [] r = FXMLController.controlador.resultado; 
       
       exelente.setText(String.valueOf(r[0]));
       muitoBom.setText(String.valueOf(r[1]));
       bom.setText(String.valueOf(r[2]));
       regular.setText(String.valueOf(r[3]));
       preocupante.setText(String.valueOf(r[4]));
        
    }    
    
}
