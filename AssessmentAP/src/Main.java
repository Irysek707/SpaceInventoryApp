import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Edinburgh College 2022
 * Assessment for Data Structures
 * Author: Anna Podlasek
 * Student ID: EC1842981
 * Date: 11/10/2022
 */

public class Main
{

    public static void main(String[] args)
    {

        System.out.println("----------------------------------------------------\n Welcome to the space inventory management program.\n---------------------------------------------------- \n");

        System.out.println("************\n Mission 1: \n************");

        InventoryList inventory1 = new InventoryList();
        Item[] mission1 = inventory1.loadData("Poddata1.txt");
        transport(mission1);


        System.out.println("************\n Mission 2: \n************");

        InventoryList inventory2 = new InventoryList();
        Item[] mission2 = inventory2.loadData("Poddata2.txt");
        transport(mission2);


        System.out.println("************\n Mission 3: \n************");

        InventoryList inventory3 = new InventoryList();
        Item[] mission3 = inventory3.loadData("Poddata3.txt");
        transport(mission3);


        System.out.println("************\n Mission 4: \n************");

        InventoryList inventory4 = new InventoryList();
        Item[] mission4 = inventory4.loadData("Poddata4.txt");
        transport(mission4);


        System.out.println("************\n Mission 5: \n************");

        InventoryList inventory5 = new InventoryList();
        Item[] mission5 = inventory5.loadData("Poddata5.txt");
        transport(mission5);


        System.out.println("************\n Mission 6: \n************");

        InventoryList inventory6 = new InventoryList();
        Item[] mission6 = inventory6.loadData("Poddata6.txt");
        transport(mission6);


        System.out.println("************\n Mission 7: \n************");

        InventoryList inventory7 = new InventoryList();
        Item[] mission7 = inventory7.loadData("Poddata7.txt");
        transport(mission7);


    }

    private static void transport(Item[] mission)
    {
        for(Item supplies : mission)
        {
            System.out.println(supplies);
        }

        if (mission.length>0)
        {
            Stack<Item> pod1 = new Stack<>();
            Stack<Item> pod2 = new Stack<>();
            Stack<Item> warehouse = new Stack<>();
            int pod1Weight = 0;
            int pod2Weight = 0;
            int warehouseWeight = 0;

            System.out.println("\nStatus of pods before loading:\n" + "Pod 1: " + pod1.size() + " items.\nPod 2: " + pod2.size() +" items.");

            for (int k = 0; k < mission.length; k++)
            {

                //Rules for dividing items into pods.
                if ((pod1.size() < 9) && (pod1Weight + mission[k].getWeight() < 1600))
                {
                    pod1.push(mission[k]);
                    pod1Weight = pod1Weight + mission[k].getWeight();

                }
                else if ((pod2.size() < 9) && (pod2Weight < 1600))
                {
                    pod2.push(mission[k]);
                    pod2Weight = pod2Weight + mission[k].getWeight();
                }
                else
                {
                    warehouse.push(mission[k]);
                    warehouseWeight = warehouseWeight + mission[k].getWeight();
                }
            }

            System.out.println("\n----------------------------------------------------\n Items moving to the pods.\n----------------------------------------------------");

            if (pod1.isEmpty())
            {
                System.out.println("There is no items available to transfer.");
            }
            else
            {
                System.out.println("Pod 1 items: " + pod1.size() + ". Pod weight: " + pod1Weight + " kg. \nItems: \n" + pod1 + "\n");
                System.out.println("Pod 2 items: " + pod2.size() + ". Pod weight: " + pod2Weight + " kg. \nItems: \n" + pod2 + "\n");

                if (warehouse.size() > 0)
                {
                    System.out.println("Items left on Earth: " + warehouse.size() + ". Items weight: " + warehouseWeight + " kg. \nItems: \n" + warehouse + "\n");
                }
            }

            System.out.println("----------------------------------------------------\n Landing on a space station.\n Unloading items in a space station.\n Total items shipped: " + (pod1.size() + pod2.size()) +"\n Total weight: " + (pod1Weight + pod2Weight) + "\n----------------------------------------------------");

            //Creating a queue with limit of 18 items, as max capacity of each pod = 9 and there is only 2 pods.
            int LIMIT = 18;
            BlockingQueue<Item> corridor = new ArrayBlockingQueue<>(LIMIT);
            System.out.println("Unloading Pod 1 to the queue:");
            for (int p = 0; pod1.size() > 0; p++)
            {
                corridor.add(pod1.pop());
            }
            int corridorWeight = pod1Weight;

            System.out.println(corridor + "\nTotal items in the corridor: " + corridor.size() + "\nTotal weight: " + corridorWeight);

            System.out.println("\nUnloading Pod 2 to the queue:");
            for (int p = 0; pod2.size() > 0; p++)
            {
                corridor.add(pod2.pop());
            }
            corridorWeight = corridorWeight + pod2Weight;
            System.out.println(corridor + "\nTotal items in the corridor: " + corridor.size() + "\nTotal weight: " + corridorWeight);

            System.out.println("\n----------------------------------------------------\n Moving items from the corridor to the bays \n----------------------------------------------------");

            List<Item> bay1 = new LinkedList<>();
            List<Item> bay2 = new LinkedList<>();
            List<Item> bay3 = new LinkedList<>();

            //Moving items form queue to temporary Array.
            Item[] tempQueue = corridor.toArray(new Item[0]);

            for (int j = 0; j < tempQueue.length; j++)
            {
                //Rules for dividing into bays.
                if (tempQueue[j].getCargoID().startsWith("F"))
                {
                    bay1.add(tempQueue[j]);
                }
                else if (tempQueue[j].getCargoID().startsWith("T"))
                {
                    bay2.add(tempQueue[j]);
                }
                else if (tempQueue[j].getCargoID().startsWith("P"))
                {
                    bay3.add(tempQueue[j]);
                }
            }


            System.out.println("Bay1 - Food:\n" + bay1 + "\nItems in a bay: " + bay1.size() + "\n");
            System.out.println("Bay2 - Technical:\n" + bay2 + "\nItems in a bay: " + bay2.size() + "\n");
            System.out.println("Bay 3 - Personal:\n" + bay3 + "\nItems in a bay: " + bay3.size() + "\n");

            //Emptying every instance to make them ready for next mission.
            pod1.empty();
            pod2.empty();
            warehouse.empty();
            corridor.clear();
            bay1.clear();
            bay2.clear();
            bay3.clear();

        }
        else
        {
            System.out.println("Shipment on this mission is not possible.");
        }
    }


}