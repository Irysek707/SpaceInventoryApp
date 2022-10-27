import java.util.Objects;

/**
 * Edinburgh College 2022
 * Assessment for Data Structures
 * Author: Anna Podlasek
 * Student ID: EC1842981
 * Date: 11/10/2022
 */


public class Item
{
    private String cargoID;
    private int weight;
    private String description;

    public Item()
    {
        cargoID = "";
        weight = 0;
        description = "";
    }

    // Constructor //
    public Item(String cargoID, int weight, String description)
    {
        this.cargoID = cargoID;
        this.weight = weight;
        this.description = description;
    }

    //Booleans to find out if attributes are valid.
    public boolean isIdValid(String cargoID)
    {
        if ((cargoID.startsWith("F")) || (cargoID.startsWith("P")) || (cargoID.startsWith("T")))
        {
            return true;
        }
        return false;
    }

    public boolean isWeightValid(String weight)
    {
        try
        {
            Integer.parseInt(weight);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    //hashCode and equals
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.cargoID);

        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        Item item = (Item) obj;
        if (item.getCargoID().equals(this.getCargoID()))
        {
            return true;
        }
        return false;

    }


    // Getters and Setters //
    public String getCargoID() { return cargoID; }

    public void setCargoID(String cargoID) { this.cargoID = cargoID; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }


    // toString method//
    @Override
    public String toString()
    {
        return "ID: " + cargoID + " - " + description +
                ", " + weight +
                "kg";
    }

}
