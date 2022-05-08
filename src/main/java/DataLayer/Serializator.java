package DataLayer;

import BuisinessLogic.MenuItem;
import BuisinessLogic.Order;

import java.io.*;
import java.util.HashMap;;
import java.util.HashSet;
import java.util.Set;

public class Serializator {

    public void serializeP(Object o) {
        FileOutputStream f;
        try {
            f = new FileOutputStream("src/main/resources/products.txt");
            ObjectOutputStream output = new ObjectOutputStream(f);
            output.writeObject(o);
            output.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<MenuItem> deserializeP() throws IOException, ClassNotFoundException {
        Set<MenuItem> menuItemSet=new HashSet<>();
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream("src/main/resources/products.txt");
        ObjectInputStream p=new ObjectInputStream(fileInputStream);
        menuItemSet=(Set<MenuItem>) p.readObject();
        p.close();
        fileInputStream.close();

        return menuItemSet;
    }

    public void serializeO(Object o){
        FileOutputStream f;
        try {
            f = new FileOutputStream("src/main/resources/orders.txt");
            ObjectOutputStream output = new ObjectOutputStream(f);
            output.writeObject(o);
            output.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Order, Set<MenuItem>> deserializeO() throws IOException, ClassNotFoundException {
        HashMap<Order, Set<MenuItem>> orderSet=new HashMap<>();
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream("src/main/resources/orders.txt");

        ObjectInputStream p=new ObjectInputStream(fileInputStream);
        orderSet=(HashMap) p.readObject();
        p.close();
        fileInputStream.close();

        return orderSet;
    }
}
