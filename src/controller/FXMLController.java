/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import jfuzzyliteexample.Fuzzy;
import jfuzzyliteexample.Principal;


/**
 *
 * @author MariaI
 */
public class FXMLController implements Initializable {
   
    Fuzzy f = new Fuzzy();
    
    float[] resultado;
   
    static FXMLController controlador;
   
    @FXML
    private JFXTextField tamanho;

    @FXML
    private JFXTextField parametro;

    
    
    @FXML
    public void verificar(){
       float t = Float.parseFloat(tamanho.getText());
       float p = Float.parseFloat(parametro.getText());
      resultado = f.tratamento(t, p);
       
       mudarTela();
       
       
        
    }
    
    private void mudarTela() {
        controlador = this; //pegando o contexto do outro controlador.
        Parent tela2;
        try {
            tela2 = FXMLLoader.load(getClass().getResource("/visao/ResultadoFXML.fxml"));
        } catch (NullPointerException | IOException ex) {
            return;
        }
            Scene scene = new Scene(tela2);
            Principal.getStagePrincipal().setScene(scene);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
