package it.polito.tdp.libretto;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.polito.tdp.libretto.model.Libretto;
import it.polito.tdp.libretto.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	private Libretto model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> comboPunti;

    @FXML
    private DatePicker selData;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtResult;

    @FXML
    void inserisci(ActionEvent event) {
    	String corso = txtNome.getText();
    	Integer punti = comboPunti.getValue();
    	LocalDate date = selData.getValue();
    	
    	Voto v = new Voto(corso, punti, date);
    	this.model.add(v);
    	
    	txtResult.setText(this.model.toString());
    }

    @FXML
    void initialize() {
        assert comboPunti != null : "fx:id=\"comboPunti\" was not injected: check your FXML file 'main.fxml'.";
        assert selData != null : "fx:id=\"selData\" was not injected: check your FXML file 'main.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'main.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'main.fxml'.";

        for(int i = 18; i < 30; i++)
        	comboPunti.getItems().add(i);
    }

	public void setModel(Libretto model) {
		this.model = model;
	}
    
    
    

}
