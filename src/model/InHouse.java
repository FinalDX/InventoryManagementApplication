package model;

/**
 * InHouse class
 *
 * @author Sean Fenner
 */
public class InHouse extends Part{
    // Private variable
    private int machineId;

    // Constructor
    public InHouse(int id, String name, double price, int stock, int min,
            int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    // =========================================================================
    // SETTER METHOD
    // =========================================================================
    
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // GETTER METHOD
    // =========================================================================

    public int getMachineId() {
        return machineId;
    }   
    
    // -------------------------------------------------------------------------
}
