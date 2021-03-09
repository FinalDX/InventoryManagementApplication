package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product class
 *
 * @author Sean Fenner
 */
 public class Product {
    // Private variables
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price; 
    private int stock;
    private int min;
    private int max;

    // Constructor
    public Product(ObservableList<Part> associatedParts, int id, String name, double price, int stock, int min, int max) {
       if (min <= max && stock <= max && stock >= min)
        {
            this.associatedParts = associatedParts;
            this.id = id;
            this.name = name;
            setPrice(price);
            setStock(stock);
            setMin(min);
            setMax(max);
        } else {
            throw new RuntimeException();
        }
    }

    // =========================================================================
    // SETTER METHODS
    // =========================================================================
    
    public void setId(int id) {
        this.id = id;
    }

    // -------------------------------------------------------------------------
    
    public void setName(String name) {
        this.name = name;
    }

    // -------------------------------------------------------------------------
    
    public final void setPrice(double price) {
        double totalPrice = 0;
        for (Part part: associatedParts)
        {
            totalPrice += part.getPrice();
        }
        if (price >= totalPrice)
        {
            this.price = price;
        } else {
            throw new RuntimeException();
        }
    }

    // -------------------------------------------------------------------------
    
    public final void setStock(int stock){
        this.stock = stock;
    }
    
    // -------------------------------------------------------------------------
    
    public final void setMin(int min){
        this.min = min;
    }
    
    // -------------------------------------------------------------------------
    
    public final void setMax(int max){
        this.max = max;
    }
    
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // GETTER METHODS
    // =========================================================================
    
    public int getId() {
        return id;
    }

    // -------------------------------------------------------------------------
    
    public String getName() {
        return name;
    }

    // -------------------------------------------------------------------------
    
    public double getPrice() {
        return price;
    }

    // -------------------------------------------------------------------------
    
    public int getStock() {
        return stock;
    }

    // -------------------------------------------------------------------------
    
    public int getMin() {
        return min;
    }

    // -------------------------------------------------------------------------
    
    public int getMax() {
        return max;
    }
    
    // -------------------------------------------------------------------------
    
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
    
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // PUBLIC METHODS
    // =========================================================================
    
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    
    // -------------------------------------------------------------------------
    
    public void deleteAssociatedPart(Part associatedPart){
        associatedParts.remove(associatedPart);
    }
    
    // -------------------------------------------------------------------------
}