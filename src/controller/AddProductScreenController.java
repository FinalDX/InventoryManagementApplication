package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Add Product Screen Controller class
 *
 * @author Sean Fenner
 */
public class AddProductScreenController implements Initializable {

    private Product newProduct;
    
    // Text field variables
    @FXML private TextField partSearchField;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceCostField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    
    // Parts outside of product table variables
    @FXML private TableView<Part> outProductTable;
    @FXML private TableColumn<Part, Integer> outPartIdColumn;
    @FXML private TableColumn<Part, String> outPartNameColumn;
    @FXML private TableColumn<Part, Integer> outInventoryLevelColumn;
    @FXML private TableColumn<Part, Integer> outPricePerUnitColumn;
    
    // Parts inside of product table variables
    @FXML private TableView<Part> inProductTable;
    @FXML private TableColumn<Part, Integer> inPartIdColumn;
    @FXML private TableColumn<Part, String> inPartNameColumn;
    @FXML private TableColumn<Part, Integer> inInventoryLevelColumn;
    @FXML private TableColumn<Part, Integer> inPricePerUnitColumn;
    
    // =========================================================================
    // BUTTON PUSHED MEHTODS
    // =========================================================================
    
    public void searchOutProductButtonPushed()
    {
        for (Part part: Inventory.getAllParts())
        {
            if (part.getName().contains(partSearchField.getText()) || 
                    part.getName().equalsIgnoreCase(partSearchField.getText()))
            {
                outProductTable.getSelectionModel().select(part);
            }
        }
    }
    
    // -------------------------------------------------------------------------
    
    public void addButtonPushed()
    { 
        if (!(outProductTable.getSelectionModel().isEmpty()))
        {
            inProductTable.getItems().add(outProductTable.getSelectionModel().getSelectedItem());
        }
    }
    
    // -------------------------------------------------------------------------
    
    public void deleteButtonPushed()
    {
        if (!(inProductTable.getSelectionModel().isEmpty()))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure"
                    + " you want to remove the selected part from this product?");
            Optional<ButtonType> button = alert.showAndWait();
            if (button.get() == ButtonType.OK)
            {
                inProductTable.getItems().remove(inProductTable.getSelectionModel().getSelectedItem());
            }
        }
    }
    
    // -------------------------------------------------------------------------
    
    public void saveButtonPushed(ActionEvent event) throws IOException
    {
        newProduct = createProduct();
        if (newProduct != null)
        {
            Parent mainParent = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            Scene mainScene = new Scene(mainParent);
        
            Inventory.addProduct(newProduct);
        
            Stage stage;
            stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
            stage.setScene(mainScene);
            stage.show();
        }
    }
    
    // -------------------------------------------------------------------------
    
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
    
    // =========================================================================
    // OTHER METHODS
    // =========================================================================
    
    public Product createProduct()
    {   
        try 
        {
            ObservableList<Part> associatedParts;
            int id;
            String name;
            int inv;
            double price;
            int max;
            int min;
        
            associatedParts = inProductTable.getItems();
            id = Inventory.getAllProducts().size();
             name = nameField.getText();
             inv = Integer.parseInt(invField.getText());
             price = Double.parseDouble(priceCostField.getText());
             max = Integer.parseInt(maxField.getText());
             min = Integer.parseInt(minField.getText());
        
            id += 1;

            newProduct = new Product(associatedParts, id, name, price, inv, min, max);
            
            if (associatedParts.isEmpty())
            {
                throw new RuntimeException();
            }
  
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Values");
            alert.setContentText("Values in text fields are incorrect.");
            alert.showAndWait();
            newProduct = null;
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Values");
            alert.setContentText("Cannot add product. \n"
                    + "Please ensure the following conditions: \n"
                    + "1) Max value is larger than or equal to min value. \n"
                    + "2) Inv value is between max value and min value. \n"
                    + "3) There is at least one part in the product. \n"
                    + "4) Price value must be greater than or equal to the \n"
                    + "  sum total price of all parts in the product.");
            alert.showAndWait();
            newProduct = null;
        }
        
	return newProduct;
    }
    
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // INITIALIZE METHOD
    // =========================================================================
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        outPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        outPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        outInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        outPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        outProductTable.setItems(Inventory.getAllParts());
        
        inPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        inPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    // -------------------------------------------------------------------------
}
