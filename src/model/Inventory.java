package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class
 *
 * @author Sean Fenner
 */
public class Inventory {
    // Private variables
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    // =========================================================================
    // PUBLIC METHODS
    // =========================================================================
    
    public static void addPart(Part newPart)
    {
        allParts.add(newPart);
    };
    
    // -------------------------------------------------------------------------
    
    public static void addProduct(Product newProduct)
    {
        allProducts.add(newProduct);
    };
    
    // -------------------------------------------------------------------------
    
    public static Part lookUpPart(int id)
    {
        for (Part part : allParts) 
        {
            if (part.getId() == id)
            {
                return part;
            }
        }
        System.out.println("No part found");
        return null;
    }
    
    // -------------------------------------------------------------------------
    
    public static ObservableList<Part> lookUpPart(String name)
    {
        for (Part part : allParts) 
        {
            if (part.getName().equals(name))
            {
                return allParts;
            }
        }
        System.out.println("No part found");
        return null;    
    }
    
    // -------------------------------------------------------------------------
    
    public static Product lookUpProductt(int id)
    {
        for (Product product : allProducts) 
        {
            if (product.getId() == id)
            {
                return product;
            }
        }
        System.out.println("No product found");
        return null;
    }
    
    // -------------------------------------------------------------------------
    
    public static ObservableList<Product> lookUpProduct(String name)
    {
        for (Product product : allProducts) 
        {
            if (product.getName().equals(name))
            {
                return allProducts;
            }
        }
        System.out.println("No product found");
        return null;    
    }
    
    // -------------------------------------------------------------------------
    
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index, selectedPart);
    }
    
    // -------------------------------------------------------------------------
    
    public static void updateProduct(int index, Product selectedProduct)
    {
        allProducts.set(index, selectedProduct);
    }
    
    // -------------------------------------------------------------------------
    
    public static void deletePart(Part selectedPart)
    {
        allParts.remove(selectedPart);
    }
    
    // -------------------------------------------------------------------------
    
    public static void deleteProduct(Product selectedProduct)
    {
        allProducts.remove(selectedProduct);
    }
    
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // GETTER METHODS
    // =========================================================================
    
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    
    // -------------------------------------------------------------------------
    
     public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }
    
     // -------------------------------------------------------------------------
}
