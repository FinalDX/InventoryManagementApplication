package controller;

import model.InHouse;
import model.Outsourced;
import model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Inventory;

/**
 * FXML Add Part Screen Controller class
 *
 * @author Sean Fenner
 */
public class AddPartScreenController implements Initializable {
    
    private Part newPart;
    
    // Radio button variables
    @FXML private RadioButton inHouseRadioButton;
    @FXML private RadioButton outsourcedRadioButton;
    @FXML private Label radioButtonLabel;
    private ToggleGroup partTypeToggleGroup;
    
    // Text field variables
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceCostField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private TextField radioField;
    
    // =========================================================================
    // BUTTON PUSHED METHODS
    // =========================================================================
    
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        Parent mainParent = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene mainScene = new Scene(mainParent);
        
        Stage stage;
        stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
    
    // -------------------------------------------------------------------------
    
    public void saveButtonPushed(ActionEvent event) throws IOException
    {
        newPart = createPart();
        if (newPart != null)
        {
            Parent mainParent = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            Scene mainScene = new Scene(mainParent);
        
            Inventory.addPart(newPart);
      
            Stage stage;
            stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
            stage.setScene(mainScene);
            stage.show();
        }
    }
    
    // -------------------------------------------------------------------------
    
    public void radioButtonChanged()
    {
        if (this.partTypeToggleGroup.getSelectedToggle().equals(this.inHouseRadioButton))
        {
            radioButtonLabel.setText("Machine ID  ");
        }
        if (this.partTypeToggleGroup.getSelectedToggle().equals(this.outsourcedRadioButton))
        {
            radioButtonLabel.setText("Company Name");
        }
    }
    
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // OTHER METHODS
    // ========================================================================= 
    
    public Part createPart()
    {   
        try
        {
            int id;
            String name;
            int inv;
            double price;
            int max;
            int min;
        
            id = Inventory.getAllParts().size();
            name = nameField.getText();
            inv = Integer.parseInt(invField.getText());
            price = Double.parseDouble(priceCostField.getText());
            max = Integer.parseInt(maxField.getText());
            min = Integer.parseInt(minField.getText());
        
            id += 1;
       
            if (this.partTypeToggleGroup.getSelectedToggle().equals(this.inHouseRadioButton))
            {
                int machineId = Integer.parseInt(radioField.getText());
                newPart = new InHouse(id, name, price, inv, min, max, machineId);
            }
            if (this.partTypeToggleGroup.getSelectedToggle().equals(this.outsourcedRadioButton))
                {
                String companyName = radioField.getText();
                newPart = new Outsourced(id, name, price, inv, min, max, companyName);
            }
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Values");
            alert.setContentText("Values in text fields are incorrect.");
            alert.showAndWait();
            newPart = null;
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Values");
            alert.setContentText("Cannot add part. \n"
                    + "Please ensure the following conditions: \n"
                    + "1) Max value is larger than or equal to min value. \n"
                    + "2) Inv value is between max value and min value.");
            alert.showAndWait();
            newPart = null;
        }
        
	return newPart;
    }
   
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // INITIALIZE METHOD
    // =========================================================================
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioButtonLabel.setText("Machine ID"); 
        partTypeToggleGroup = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(partTypeToggleGroup);
        this.outsourcedRadioButton.setToggleGroup(partTypeToggleGroup);
        this.inHouseRadioButton.setSelected(true);
        
        // TODO
    }    
    
    // -------------------------------------------------------------------------
}
