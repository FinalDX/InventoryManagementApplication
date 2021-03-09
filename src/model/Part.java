package model;

/**
 * Part class
 *
 * @author Sean Fenner
 */
public abstract class Part {
    // Private variables
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    // Constructor
    public Part(int id, String name, double price, int stock, int min, int max) {
        if (min <= max && stock <= max && stock >= min)
        {
            this.id = id;
            this.name = name;
            this.price = price;
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
    
    public void setId(int id){
        this.id = id;
    }
    
    // -------------------------------------------------------------------------
    
    public void setName(String name){
        this.name = name;
    }
    
    // -------------------------------------------------------------------------
    
    public void setPrice(double price){
        this.price = price;
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
}
