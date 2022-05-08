package DataLayer;

import BuisinessLogic.MenuItem;
import BuisinessLogic.Order;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Bill {
    public void createBill(HashMap<Order , Set<MenuItem>> orders, Order order){
        try {
            File newfile = new File("src/main/resources/bill" + order.getID() + ".txt");
            java.io.FileWriter w = new java.io.FileWriter(newfile);
            w.write(order.toString()+"\n");
            for(MenuItem m:orders.get(order)){
                w.write(m.toString()+"\n");
            }
            int priceAllOrder=0;
            for(MenuItem item:orders.get(order)){
                priceAllOrder=priceAllOrder+item.getPrice();
            }
            w.write("Order price: "+priceAllOrder);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
