package model;

/**
 * Outsourced class
 *
 * @author Sean Fenner
 */
public class Outsourced extends Part{
    // Private variable
    private String companyName;
    
    // Constructor
    public Outsourced(int id, String name, double price, int stock, int min, 
            int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    // =========================================================================
    // SETTER METHOD
    // =========================================================================

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // GETTER METHOD
    // =========================================================================

    public String getCompanyName() {
        return companyName;
    }
    
    // -------------------------------------------------------------------------
}
