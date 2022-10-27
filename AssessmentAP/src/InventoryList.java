import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Edinburgh College 2022
 * Assessment for Data Structures
 * Author: Anna Podlasek
 * Student ID: EC1842981
 * Date: 11/10/2022
 */

public class InventoryList {

    public InventoryList() {}

    public Item[] loadData(String filename)
    {
        File myFile = new File(filename);
        ArrayList<Item> arrList = new ArrayList<>();

        try
        {
            FileReader fr = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fr);

            String data = "";

            while((data = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(data, ",");

                while (st.hasMoreTokens())
                {
                    Item item = new Item();

                    String idToken = st.nextToken().trim();
                    if (item.isIdValid(idToken))
                    {
                        item.setCargoID(idToken);
                    }
                    else
                    {
                        System.out.println("Attention: Cargo item ID invalid - skipped in this shipment.");
                        break;
                    }

                    String weightToken = st.nextToken().trim();
                    if (item.isWeightValid(weightToken))
                    {
                        item.setWeight(Integer.parseInt(weightToken));
                    }
                    else
                    {
                        System.out.println("Attention: Cargo item weight format invalid - skipped in this shipment.");
                        break;
                    }

                    item.setDescription(st.nextToken().trim());

                    arrList.add(item);


                }
            }
            br.close();
        }
        catch (IOException e)
        {
            System.out.println("Error loading data");
        }

        System.out.println("\nThere is " + arrList.size() + " cargo items waiting to be shipped on this journey. \n----------------------------------------------------");

        Item result[] = new Item[arrList.size()];
        result = arrList.toArray(new Item[0]);

        return result;
    }

}
