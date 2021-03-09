package controller;

import model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Product;


/**
 * FXML Main Screen Controller class
 *
 * @author Sean Fenner
 */
public class MainScreenController implements Initializable {
    
    private Part selectedPart;
    private Product selectedProduct;
    private int selectedIndex;
    
    // Text field variables
    @FXML private TextField partSearchField;
    @FXML private TextField productSearchField;
    
    // Part table view variables
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> inventoryLevelColumn;
    @FXML private TableColumn<Part, Integer> pricePerUnitColumn;
    
    // Product table view variables
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> productIdColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> productInvLevelColumn;
    @FXML private TableColumn<Product, Integer> productPricePerUnitColumn;
    
    
    
    // =========================================================================
    // PART BUTTON PUSHED MEHTODS
    // =========================================================================
    
    public void searchPartButtonPushed()
    {
        for (Part part: Inventory.getAllParts())
        {
            if (part.getName().contains(partSearchField.getText()) || 
                    part.getName().equalsIgnoreCase(partSearchField.getText()))
            {
                partTable.getSelectionModel().select(part);
            }
        }
    }
    
    // -------------------------------------------------------------------------
    
    public void addPartButtonPushed(ActionEvent event) throws IOException
    {
	Parent addPartParent = FXMLLoader.load(getClass().getResource("/view/AddPartScreen.fxml"));
	Scene addPartScene = new Scene(addPartParent);

	Stage stage;
        stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
	stage.setScene(addPartScene);
	stage.show();
    }
    
    // -------------------------------------------------------------------------
    
    public void modifyPartButtonPushed(ActionEvent event) throws IOException
    {
        if (!(partTable.getSelectionModel().isEmpty()))
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartScreen.fxml"));
            Parent modifyPartParent = loader.load();
            Scene modifyPartScene = new Scene(modifyPartParent);
               
            ModifyPartScreenController controller = loader.getController();
            selectedIndex = partTable.getSelectionModel().getSelectedIndex();
            selectedPart =  partTable.getSelectionModel().getSelectedItem();
            controller.sendPart(selectedIndex, selectedPart);
        
            Stage stage;
            stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
            stage.setScene(modifyPartScene);
            stage.show();
        }
    }
    
    // -------------------------------------------------------------------------
    
    public void deletePartButtonPushed()
    {
        if (!(partTable.getSelectionModel().isEmpty()))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure"
                    + " you want to delete the selected part?");
            Optional<ButtonType> button = alert.showAndWait();
            if (button.get() == ButtonType.OK)
            {
                Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());
            }
        }
    }
    
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // PRODUCT BUTTON PUSHED METHODS
    // =========================================================================
    
    public void searchProductButtonPushed()
    {
        for (Product product: Inventory.getAllProducts())
        {
            if (product.getName().contains(productSearchField.getText()) || 
                    product.getName().equalsIgnoreCase(productSearchField.getText()))
            {
                productTable.getSelectionModel().select(product);
            }
        }
    }
    
    // -------------------------------------------------------------------------
    
    public void addProductButtonPushed(ActionEvent event) throws IOException
    {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/view/AddProductScreen.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        
        Stage stage;
        stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
        stage.setScene(addProductScene);
        stage.show();
    }
    
    // -------------------------------------------------------------------------
    
    public void modifyProductButtonPushed(ActionEvent event) throws IOException
    {
        if (!(productTable.getSelectionModel().isEmpty()))
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductScreen.fxml"));
            Parent modifyProductParent = loader.load();
            Scene modifyProductScene = new Scene(modifyProductParent);
      
            ModifyProductScreenController controller = loader.getController();
            selectedIndex = productTable.getSelectionModel().getSelectedIndex();
            selectedProduct =  productTable.getSelectionModel().getSelectedItem();
            controller.sendProduct(selectedIndex, selectedProduct);

            Stage stage;
            stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
            stage.setScene(modifyProductScene);
            stage.show();
        }
    }
    
    // -------------------------------------------------------------------------
    
    public void deleteProductButtonPushed()
    {
        if (!(productTable.getSelectionModel().isEmpty()))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure"
                    + " you want to delete the selected product?");
            Optional<ButtonType> button = alert.showAndWait();
            if (button.get() == ButtonType.OK)
            {
                Inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
            }
        }
    }
    
    // -------------------------------------------------------------------------
    
    public void exitButtonPushed()
    {
        Platform.exit();   
    }
    
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // INITIALIZE METHOD 
    // =========================================================================
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        partTable.setItems(Inventory.getAllParts());
        
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productTable.setItems(Inventory.getAllProducts());   
    }    
    
    // -------------------------------------------------------------------------   
}
